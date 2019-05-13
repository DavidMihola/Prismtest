# Prismtest

Just a small test repo to illustrate a build problem with Proguard and Arrow.

`./gradlew clean app:assembleRelease` will fail at the task `:app:transformClassesAndResourcesWithProguardForRelease`.

Updating the Android Gradle Plugin to 3.4.0 "solves" the problem, presumably because that version by default uses R8 instead of Proguard.
