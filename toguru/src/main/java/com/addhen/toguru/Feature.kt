package com.addhen.toguru

import kotlinx.serialization.Serializable

@Serializable
data class Feature(
    val name: String,
    val description: String = "",
    val isEnabled: Boolean = true
)
