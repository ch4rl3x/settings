package de.charlex.settings.core.encryption

import de.charlex.settings.core.Keyed

enum class Speed(override val key: String) : Keyed {

    Slow("slow"),
    Medium("medium"),
    Fast("fast")
}
