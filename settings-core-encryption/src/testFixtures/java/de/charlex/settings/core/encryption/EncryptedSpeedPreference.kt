package de.charlex.settings.core.encryption

class EncryptedSpeedPreference(override val preferenceKey: String) :
    de.charlex.settings.core.IEncryptedPreference<String> {

    val Slow: de.charlex.settings.core.EncryptedPreferenceValue<String> =
        de.charlex.settings.core.EncryptedPreferenceValue(this, "slow")
    val Medium: de.charlex.settings.core.EncryptedPreferenceValue<String> =
        de.charlex.settings.core.EncryptedPreferenceValue(this, "medium")
    val Fast: de.charlex.settings.core.EncryptedPreferenceValue<String> =
        de.charlex.settings.core.EncryptedPreferenceValue(this, "fast")

    override val defaultValue = Medium.value
}
