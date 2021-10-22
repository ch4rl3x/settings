package de.charlex.settings.datastore.encryption

import de.charlex.settings.datastore.Keyed

enum class Speed(override val key: String) : Keyed {
    Slow("slow"),
    Medium("medium"),
    Fast("fast")
}
