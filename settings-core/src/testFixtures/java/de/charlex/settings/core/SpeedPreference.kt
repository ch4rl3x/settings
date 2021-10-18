package de.charlex.settings.core

class SpeedPreference(override val preferenceKey: String) :
    IPreference<String> {

    val Slow: PreferenceValue<String> =
        PreferenceValue(this, "slow")
    val Medium: PreferenceValue<String> =
        PreferenceValue(this, "medium")
    val Fast: PreferenceValue<String> =
        PreferenceValue(this, "fast")

    override val defaultValue = Medium.value
}
