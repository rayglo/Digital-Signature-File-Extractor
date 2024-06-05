import de.comahe.i18n4k.gradle.plugin.i18n4k
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    id("de.comahe.i18n4k") version "0.8.1"
}


kotlin {
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation("org.bouncycastle:bcpkix-jdk18on:1.78.1")
            implementation("com.darkrockstudios:mpfilepicker:3.1.0")
            implementation("de.comahe.i18n4k:i18n4k-core:0.8.1")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

i18n4k {
    sourceCodeLocales = listOf("en", "it")
}


compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            packageName = "Digital Signature File Extractor"

            modules("jdk.unsupported")

            windows {
                packageVersion = "0.1"
            }
        }
    }
}
