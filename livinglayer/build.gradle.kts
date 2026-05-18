plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "io.livinglayer"
    compileSdk = 35

    defaultConfig {
        minSdk = 29
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.7.0")
    implementation("androidx.compose.foundation:foundation:1.7.0")

    testImplementation("junit:junit:4.13.2")
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "io.livinglayer"
            artifactId = "livinglayer"
            version = "1.0.0-alpha01"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("LivingLayer")
                description.set("GPU-native reactive Android UI layers with glass, holographic shaders, and sensor-aware depth.")
                url.set("https://github.com/livinglayer/livinglayer")

                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("livinglayer")
                        name.set("LivingLayer")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/livinglayer/livinglayer.git")
                    developerConnection.set("scm:git:ssh://git@github.com/livinglayer/livinglayer.git")
                    url.set("https://github.com/livinglayer/livinglayer")
                }
            }
        }
    }
}
