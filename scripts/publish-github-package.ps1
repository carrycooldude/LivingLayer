Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

if (-not $env:GITHUB_ACTOR) {
    $env:GITHUB_ACTOR = "carrycooldude"
}

if (-not $env:GITHUB_TOKEN) {
    if (Get-Command gh -ErrorAction SilentlyContinue) {
        $env:GITHUB_TOKEN = gh auth token
    }
}

if (-not $env:GITHUB_TOKEN) {
    Write-Error "GITHUB_TOKEN is required to publish to GitHub Packages."
}

.\gradlew.bat publishReleasePublicationToGitHubPackagesRepository

