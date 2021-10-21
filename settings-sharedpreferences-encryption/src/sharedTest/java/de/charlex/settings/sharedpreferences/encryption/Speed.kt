package de.charlex.settings.sharedpreferences.encryption

import de.charlex.settings.sharedpreferences.Keyed

enum class Speed(override val key: String) : Keyed {

    Slow("slow"),
    Medium("medium"),
    Fast("fast")
}
