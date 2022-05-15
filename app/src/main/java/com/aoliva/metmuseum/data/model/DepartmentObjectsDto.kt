package com.aoliva.metmuseum.data.model

import com.squareup.moshi.Json

data class DepartmentObjectsDto(
    @Json(name = "total") val count: Int, @Json(name = "objectIDs") val objectIds: List<Int>
)