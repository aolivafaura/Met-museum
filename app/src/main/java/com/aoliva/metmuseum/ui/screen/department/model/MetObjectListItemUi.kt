package com.aoliva.metmuseum.ui.screen.department.model

import androidx.compose.runtime.Immutable

@Immutable
class MetObjectListItemUi(
    val id: String,
    val title: String,
    val author: String? = null,
    val image: String? = null,
)