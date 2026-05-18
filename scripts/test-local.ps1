Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

if (Test-Path ".\gradlew.bat") {
    .\gradlew.bat publishToMavenLocal :livinglayer:testReleaseUnitTest :sample:assembleDebug
    exit $LASTEXITCODE
}

Write-Error "No Gradle wrapper was found."

