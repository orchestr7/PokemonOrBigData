plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "2.0.20"
}

kotlin {
    jvm()
    js(IR) {
        browser()
        useCommonJs()
    }

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.2")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
