Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

if (Test-Path ".\gradlew.bat") {
    .\gradlew.bat clean publishToMavenLocal
    exit $LASTEXITCODE
}

if (Get-Command gradle -ErrorAction SilentlyContinue) {
    gradle clean publishToMavenLocal
    exit $LASTEXITCODE
}

Write-Error "No Gradle wrapper or gradle executable was found. Open the project in Android Studio or install Gradle, then rerun this script."

