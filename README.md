# Settings

<a href="https://github.com/Ch4rl3x/Settings/actions?query=workflow%3ABuild"><img src="https://github.com/ch4rl3x/settings/actions/workflows/build.yml/badge.svg" alt="Build"></a>
<a href="https://www.codefactor.io/repository/github/ch4rl3x/settings"><img src="https://www.codefactor.io/repository/github/ch4rl3x/settings/badge" alt="CodeFactor" /></a>
<a href="https://repo1.maven.org/maven2/de/charlex/settings/settings-datastore/"><img src="https://img.shields.io/maven-central/v/de.charlex.settings/settings-datastore" alt="Maven Central" /></a>

Settings is an easy wrapper with type safety for SharedPreferences and Datastore. By using it, you will no longer need to use Strings as keys. Instead, each Preference is represented by an object.


## Features

- Type safety for SharedPreferences and Datastore
- Simple usage for both SharedPrefernces and Datastore
- Encryption for SharedPreferences and **Datastore**

## Dependency

Add the library to your module `build.gradle`
```gradle
dependencies {
    implementation 'de.charlex.settings:settings-datastore:1.0.0-rc04'
    implementation 'de.charlex.settings:settings-datastore-encryption:1.0.0-rc04'
    implementation 'de.charlex.settings:settings-sharedpreferences:1.0.0-rc04'
    implementation 'de.charlex.settings:settings-sharedpreferences-encryption:1.0.0-rc04'
}
```

## Usage

### Declaring Preferences

```kotlin
enum class ExampleEnumWithKey(val preferenceKey: String) {
    Value1("value1_key"),
    Value2("value2_key"),
    Value3("value3_key")
}

enum class SimpleEnum {
    Value1,
    Value2,
    Value3
}
```

```kotlin
object Preferences {
    val preferenceInt = intPreference("preference_int", 1)
    val preferenceString = stringPreference("preference_string", "default")
    val preferenceFloat = floatPreference("preference_float", 1.1f)
    val preferenceLong = longPreference("preference_long", 1L)
    val preferenceBoolean = boolenPreference("preference_boolean", true)

    // enum preferences have to specify which function to use for retrieving the key
    val preferenceExampleEnumWithKey = enumPreference("preference_enum_with_key", ExampleEnumWithKey.Value2, ExampleEnumWithKey::preferenceKey)
    val preferenceExampleEnumWithOrdinal = enumPreference("preference_enum_with_ordinal", SimpleEnum.Value2, SimpleEnum::ordinal)
    val preferenceExampleEnumWithName = enumPreference("preference_enum_with_name", SimpleEnum.Value2, SimpleEnum::name)

    val preferenceStringEncrypted = encryptedStringPreference("preference_string_encrypted", "default")
}
```

### Using Preferences

```kotlin
val settings = Settings.create(context)
val encryptedSettings = Settings.createEncrypted(context)

//Read
val exampleString: String = settings.get(Preferences.PreferenceString)
val exampleEnumWithKey: ExampleEnumWithKey = settings.get(Preferences.preferenceExampleEnumWithKey)

val encryptedExampleString: String = encryptedSettings.get(Preferences.preferenceStringEncrypted)

//Write
settings.put(Preferences.preferenceString, "my value")
settings.put(Preferences.preferenceExampleEnumWithKey, ExampleEnumWithKey.Value1)
settings.put(Preferences.preferenceExampleEnumWithOrdinal, SimpleEnum.Value1)

encryptedSettings.put(Preferences.preferenceStringEncrypted, "my secrets")

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
