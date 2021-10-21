package de.charlex.settings.sharedpreferences


enum class Speed(override val key: String): Keyed {
    Slow("slow"),
    Medium("medium"),
    Fast("fast")
}