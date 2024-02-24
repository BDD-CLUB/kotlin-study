plugins {
    kotlin("jvm") version "1.9.0"
}

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation("com.github.woowacourse-projects:mission-utils:1.1.0")
    /**
     * mockito를 사용하면 any! 타입이 반환되어 NPE가 터짐. 모킹을 너무 사용하고 싶기 때문에 라이브러리 추가...
     */
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
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
