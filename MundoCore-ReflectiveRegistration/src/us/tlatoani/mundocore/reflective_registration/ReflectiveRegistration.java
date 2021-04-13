package us.tlatoani.mundocore.reflective_registration;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.*;
import us.tlatoani.mundocore.base.Logging;
import us.tlatoani.mundocore.reflection.Reflection;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tlatoani on 2/24/17.
 */
public class ReflectiveRegistration {
    public static Collection<SyntaxElementInfo<? extends Condition>> conditions;
    public static Collection<SyntaxElementInfo<? extends Effect>> effects;
    public static Collection<SyntaxElementInfo<? extends Statement>> statements;
    public static List<ExpressionInfo<?, ?>> expressions;
    public static int[] expressionTypesStartIndices;

    public static Field patternsField;

    static {
        try {
            conditions = (Collection<SyntaxElementInfo<? extends Condition>>) Reflection.getStaticField(Skript.class, "conditions");
            effects = (Collection<SyntaxElementInfo<? extends Effect>>) Reflection.getStaticField(Skript.class, "effects");
            statements = (Collection<SyntaxElementInfo<? extends Statement>>) Reflection.getStaticField(Skript.class, "statements");
            expressions = (List<ExpressionInfo<?, ?>>) Reflection.getStaticField(Skript.class, "expressions");
            expressionTypesStartIndices = (int[]) Reflection.getStaticField(Skript.class, "expressionTypesStartIndices");

            patternsField = SyntaxElementInfo.class.getDeclaredField("patterns");
            patternsField.setAccessible(true);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Logging.reportException(ReflectiveRegistration.class, e);
        }
    }

    public static void registerEffect(SyntaxElementInfo<? extends Effect> effectInfo) {
        effects.add(effectInfo);
        statements.add(effectInfo);
    }

    public static void registerCondition(SyntaxElementInfo<? extends Condition> conditionInfo) {
        conditions.add(conditionInfo);
        statements.add(conditionInfo);
    }

    public static void registerExpression(ExpressionInfo expressionInfo, ExpressionType type) {
        for (int i = type.ordinal() + 1; i < ExpressionType.values().length; i++) {
            expressionTypesStartIndices[i]++;
        }
        expressions.add(expressionTypesStartIndices[type.ordinal()], expressionInfo);
    }
}
