# Settings
Settings is an easy wrapper with type safety for SharedPreferences. By using it, you will no longer use Strings as keys

<a href="https://github.com/Ch4rl3x/Settings/actions?query=workflow%3AInstrumentation-Test"><img src="https://github.com/Ch4rl3x/Settings/workflows/Instrumentation-Test/badge.svg" alt="Instrumentation-Test"></a>
<a href="https://github.com/Ch4rl3x/Settings/actions?query=workflow%3ALint"><img src="https://github.com/Ch4rl3x/Settings/workflows/Lint/badge.svg" alt="Lint"></a>
<a href="https://github.com/Ch4rl3x/Settings/actions?query=workflow%3AKtlint"><img src="https://github.com/Ch4rl3x/Settings/workflows/Ktlint/badge.svg" alt="Ktlint"></a>

<a href="https://www.codefactor.io/repository/github/ch4rl3x/settings"><img src="https://www.codefactor.io/repository/github/ch4rl3x/settings/badge" alt="CodeFactor" /></a>
<a href="https://repo1.maven.org/maven2/de/charlex/settings/settings/"><img src="https://img.shields.io/maven-central/v/de.charlex.settings/settings" alt="Maven Central" /></a>


## Dependency

Add the library to your module `build.gradle`
```gradle
dependencies {
    implementation 'de.charlex.settings:settings-core:1.0.0-rc01'
    implementation 'de.charlex.settings:settings-core-encryption:1.0.0-rc01'
    implementation 'de.charlex.settings:settings-datastore:1.0.0-rc01'
    implementation 'de.charlex.settings:settings-datastore-encryption:1.0.0-rc01'
    implementation 'de.charlex.settings:settings-sharedpreferences:1.0.0-rc01'
    implementation 'de.charlex.settings:settings-sharedpreferences-encryption:1.0.0-rc01'
}
```

## Features
- Typed access and easy access to SharedPreferences and Preferences DataStore

## Usage

```kotlin
val settings = Settings.create(context)

//Write
settings.putString(Preferences.PreferenceString, "my value")
settings.putString(Preferences.PreferenceComplex.Slow)

//Read
settings.getString(Preferences.PreferenceString)
settings.getString(Preferences.PreferenceComplex)
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
