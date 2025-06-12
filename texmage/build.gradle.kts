plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "np.com.ishworsubedi.texmage"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"  // supported target version
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar() // optional
        }
    }
}
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("gpr") {
                from(components["release"])

                groupId = "com.github.IshworSubedi13" // Required GitHub format
                artifactId = "texmage"
                version = "1.0.1"

                pom {
                    name.set("Texmage")
                    description.set("Texmage is a powerful and easy to use Android library designed to generate stylish avatars by combining text and images seamlessly. Whether you want to display user initials, profile pictures, or custom icons with vibrant backgrounds and shapes, Texmage delivers smooth, customizable avatar rendering for your apps!")
                    url.set("https://github.com/IshworSubedi13/texmage")
                }
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/IshworSubedi13/texmage")
                credentials {
                    credentials {
                        username = (project.findProperty("gpr.user") ?: System.getenv("GITHUB_USERNAME")).toString()
                        password = (project.findProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")).toString()
                    }
                }
            }
        }
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation (libs.glide)
    annotationProcessor (libs.compiler)
}