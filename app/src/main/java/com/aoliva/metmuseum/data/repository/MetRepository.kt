package com.aoliva.metmuseum.data.repository

import com.aoliva.metmuseum.data.mockresponses.DEPARTMENTS
import com.aoliva.metmuseum.data.mockresponses.OBJECT_321412
import com.aoliva.metmuseum.data.model.DepartmentDto
import com.aoliva.metmuseum.data.model.DepartmentsResponseDto
import com.aoliva.metmuseum.data.model.MetObjectApiResponseDto
import com.aoliva.metmuseum.domain.model.MetObject
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MetRepository @Inject constructor() {

    fun getDepartments() = flow {
        val departmentsAdapter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(DepartmentsResponseDto::class.java)
        val dtos = withContext(Dispatchers.Default) {
            @Suppress("BlockingMethodInNonBlockingContext")
            departmentsAdapter.fromJson(DEPARTMENTS)?.departments ?: emptyList()
        }
        emit(DepartmentDto.toDepartment(dtos))
    }

    fun getDepartmentObjects(id: Int) {

    }

    fun getObject(id: Int) = flow {
        val metObjectAdapter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(MetObjectApiResponseDto::class.java)
        val dto = withContext(Dispatchers.Default) {
            @Suppress("BlockingMethodInNonBlockingContext")
            metObjectAdapter.fromJson(OBJECT_321412)
        }
        val metObject = dto?.let { MetObjectApiResponseDto.toMetObject(dto) } ?: MetObject.EMPTY
        emit(metObject)
    }
}