package de.charlex.settings.datastore

enum class Speed(override val key: String) : Keyed {
    Slow("slow"),
    Medium("medium"),
    Fast("fast")
}
