// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // Safe Args için ekleme
    alias(libs.plugins.androidx.navigation.safeargs.kotlin) apply false
}