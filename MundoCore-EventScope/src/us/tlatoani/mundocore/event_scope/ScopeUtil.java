package us.tlatoani.mundocore.event_scope;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.config.Node;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.skript.lang.TriggerSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tlatoani on 5/4/17.
 */
public class ScopeUtil {

    public static Optional<TriggerItem> loadSectionNode(SectionNode sectionNode, TriggerSection parent) {
        if (parent != null) {
            ScriptLoader.currentSections.add(parent);
        }
        List<TriggerItem> triggerItems = ScriptLoader.loadItems(sectionNode);
        if (parent != null && !triggerItems.isEmpty()) {
            triggerItems.get(triggerItems.size() - 1).setNext(parent.getNext());
            for (TriggerItem triggerItem : triggerItems) {
                triggerItem.setParent(parent);
            }
            ScriptLoader.currentSections.remove(parent);
        }
        return triggerItems.isEmpty() ? Optional.empty() : Optional.of(triggerItems.get(0));
    }

    public static TriggerItem loadSectionNodeOrDummy(SectionNode sectionNode, TriggerSection parent) {
        return loadSectionNode(sectionNode, parent).orElse(new DummyTriggerItem());
    }

    public static void removeSubNodes(SectionNode sectionNode) {
        List<Node> nodes = new ArrayList<>();
        for (Node node : sectionNode) {
            nodes.add(node);
        }
        nodes.forEach(Node::remove);
    }

    public static SectionNode stealSubNodes(SectionNode sectionNode) {
        SectionNode res = new SectionNode("", "", sectionNode.getParent(), -1);
        List<Node> nodes = new ArrayList<>();
        for (Node node : sectionNode) {
            nodes.add(node);
        }
        nodes.forEach(node -> node.move(res));
        return res;
    }
}