plugins {
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation("com.github.woowacourse-projects:mission-utils:1.1.0")
    implementation("org.reflections:reflections:0.10.2")
    implementation("org.jetbrains.kotlin-reflection:1.9.0")
    implementation(kotlin("reflect"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}
