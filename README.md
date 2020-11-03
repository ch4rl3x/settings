# Settings
PhotoView aims to help produce an easily usable implementation of a zooming Android ImageView.

<a href="https://github.com/Ch4rl3x/Settings/actions?query=workflow%3AInstrumentation-Test"><img src="https://github.com/Ch4rl3x/Settings/workflows/Instrumentation-Test/badge.svg" alt="Instrumentation-Test"></a>
<a href="https://github.com/Ch4rl3x/Settings/actions?query=workflow%3ALint"><img src="https://github.com/Ch4rl3x/Settings/workflows/Lint/badge.svg" alt="Lint"></a>
<a href="https://github.com/Ch4rl3x/Settings/actions?query=workflow%3AKtlint"><img src="https://github.com/Ch4rl3x/Settings/workflows/Ktlint/badge.svg" alt="Ktlint"></a>

[![](https://jitpack.io/v/Ch4rl3x/Settings.svg)](https://jitpack.io/#Ch4rl3x/Settings)

## Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    implementation 'com.github.Ch4rl3x:Settings:latest.release.here'
}
```

## Features
- Typed access and easy access to SharedPreferences

## Usage

```kotlin
val settings = SettingsImpl(context)
//Read
settings.putString(Preferences.PreferenceString, "my value")
//Write
val value: String = settings.getString(Preferences.PreferenceString)
```

```kotlin
class SpeedPreference(override val preferenceKey: String) : IPreference<String> {

    val Slow: PreferenceValue<String> = PreferenceValue(this, "slow")
    val Medium: PreferenceValue<String> = PreferenceValue(this, "medium")
    val Fast: PreferenceValue<String> = PreferenceValue(this, "fast")

    override val defaultValue = Medium.value
}
```

```kotlin
object Preferences {
    val PreferenceComplex = SpeedPreference("preference_complex")

    val PreferenceInt = Preference("preference_int", 1)
    val PreferenceString = Preference("preference_string", "default")
    val PreferenceFloat = Preference("preference_float", 1.1f)
    val PreferenceLong = Preference("preference_long", 1L)
    val PreferenceBoolean = Preference("preference_boolean", true)
}
```

That's it!

License
--------

    Copyright 2020 Alexander Karkossa

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.