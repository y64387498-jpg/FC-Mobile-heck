# AXIOM Launcher

Minimal Android launcher with one **BAŞLAT** button. It opens the installed Standoff 2 app through Android's package manager.

## Build on GitHub (phone-friendly)

1. Extract this ZIP on your phone.
2. Upload the **contents** of the extracted `AXIOM-Launcher` folder to the root of your GitHub repository. Keep the `.github/workflows/build-apk.yml` path.
3. Open the repository's **Actions** tab.
4. Select **Build AXIOM Launcher APK** and tap **Run workflow**, or push a source change.
5. When the run succeeds, open it and download the `AXIOM-Launcher-APK` artifact.
6. Extract the downloaded artifact ZIP; install `app-debug.apk`.

## Notes

- The launcher only starts the game; it does not modify game files or gameplay.
- The package name configured is `com.axlebolt.standoff2`. If your installed edition uses a different package ID, the launch target must be updated.
- Android package visibility is declared in `AndroidManifest.xml` using `<queries>`.
- This is a debug APK. A release APK should be signed with a private signing key before distribution.
