plugins {
    application
    java
}
java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }
application { mainClass.set("org.logpipeline.Main") }
repositories { mavenCentral() }
dependencies { testImplementation("org.junit.jupiter:junit-jupiter:5.11.0") }
tasks.test { useJUnitPlatform() }
