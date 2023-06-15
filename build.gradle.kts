plugins {
    kotlin("jvm") version "1.8.21"
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(11)
}

sourceSets {
    main {
        java.srcDir("src")
        kotlin.srcDir("src")
        resources.srcDir("res")
    }
}