package us.tlatoani.mundocore.gradle_plugin

class MundoExtension {
    String versionSummary
    String[] authors = []
    String website
    Command command = new Command()
    String permission
    String mainPackage
    String mainClass
    String apiVersion
    String[] depend = ['Skript']
    String[] coreModules = []
    String spigotVersion
    String skriptVersion
    String protocolLibVersion
    String bStatsVersion

    void versionSummary(String summary) {
        versionSummary = summary
    }

    void author(String author) {
        this.authors += [author]
    }

    void authors(String authors) {
        this.authors += authors.trim().split '\\s'
    }

    void authors(String... authors) {
        this.authors += authors
    }

    void website(String website) {
        this.website = website
    }

    void permission(String permission) {
        this.permission = permission
    }

    void mainPackage(String mainPackage) {
        this.mainPackage = mainPackage
    }

    void mainClass(String mainClass) {
        this.mainClass = mainClass
    }

    void apiVersion(String apiVersion) {
        this.apiVersion = apiVersion
    }

    void depend(String dependencies) {
        depend += dependencies.trim().split '\\s'
    }

    void depend(String... dependencies) {
        depend += dependencies
    }

    void coreModule(String coreModule) {
        this.coreModules += [coreModule]
    }

    void coreModules(String coreModule) {
        this.coreModules += coreModule.trim().split'\\s'
    }

    void coreModules(String... coreModules) {
        this.coreModules += coreModules
    }

    void spigot(String version) {
        spigotVersion = version
    }

    void skript(String version) {
        skriptVersion = version
    }

    void protocolLib(String version) {
        protocolLibVersion = version
    }

    void bStats(String version) {
        bStatsVersion = version
    }
}
