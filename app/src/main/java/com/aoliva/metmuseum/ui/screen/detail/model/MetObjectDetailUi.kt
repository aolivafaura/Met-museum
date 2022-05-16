package com.aoliva.metmuseum.ui.screen.detail.model

import androidx.compose.runtime.Immutable

@Immutable
data class MetObjectDetailUi(
    val id: String,
    val title: String,
    val author: String? = null,
    val image: String? = null
)