package com.aoliva.metmuseum.domain.model

data class MetObject(
    val id: String,
    val title: String,
    val author: String? = null,
    val imageLow: String? = null,
    val image: String? = null
) {
    companion object {
        val EMPTY = MetObject("", "")
    }
}
