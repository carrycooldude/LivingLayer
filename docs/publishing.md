# Publishing

## Maven Local

```bash
./gradlew publishToMavenLocal
```

Consumer:

```kotlin
implementation("io.livinglayer:livinglayer:1.0.0")
```

## Maven Central

Publish through the Maven Central Portal or Sonatype flow with signed release artifacts.

The starter publication is configured in `livinglayer/build.gradle.kts`:

```kotlin
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "io.livinglayer"
            artifactId = "livinglayer"
            version = "1.0.0-alpha01"
        }
    }
}
```

Before public release, add:

- Source jar.
- Dokka documentation jar.
- POM metadata.
- Signing.
- Release variant configuration.
- CI release workflow.

## Local Release Script

```powershell
.\scripts\release-local.ps1
```

The script uses `gradlew.bat` when present, then falls back to a global `gradle` executable.

## GitHub Packages

The package is configured for:

```text
https://maven.pkg.github.com/carrycooldude/LivingLayer
```

Publish:

```powershell
.\scripts\publish-github-package.ps1
```

Consume:

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/carrycooldude/LivingLayer")
        credentials {
            username = gprUser
            password = gprToken
        }
    }
}

dependencies {
    implementation("io.livinglayer:livinglayer:1.0.0-alpha01")
}
```
