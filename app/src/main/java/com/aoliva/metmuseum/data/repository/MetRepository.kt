package com.aoliva.metmuseum.data.repository

import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import com.aoliva.metmuseum.data.mockresponses.DEPARTMENTS
import com.aoliva.metmuseum.data.mockresponses.DEPARTMENT_3
import com.aoliva.metmuseum.data.mockresponses.OBJECT_321412
import com.aoliva.metmuseum.data.model.DepartmentDto
import com.aoliva.metmuseum.data.model.DepartmentObjectsDto
import com.aoliva.metmuseum.data.model.DepartmentsResponseDto
import com.aoliva.metmuseum.data.model.MetObjectApiResponseDto
import com.aoliva.metmuseum.domain.model.MetObject
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MetRepository @Inject constructor(private val dispatcherProvider: DispatcherProvider) {

    fun getDepartments() = flow {
        val departmentsAdapter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(DepartmentsResponseDto::class.java)
        val dtos = withContext(dispatcherProvider.default) {
            @Suppress("BlockingMethodInNonBlockingContext")
            departmentsAdapter.fromJson(DEPARTMENTS)?.departments ?: emptyList()
        }
        emit(DepartmentDto.toDepartment(dtos))
    }

    fun getDepartmentObjects(id: Int) = flow {
        val departmentObjectsAdapter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(DepartmentObjectsDto::class.java)

        val dto = withContext(dispatcherProvider.default) {
            @Suppress("BlockingMethodInNonBlockingContext")
            departmentObjectsAdapter.fromJson(DEPARTMENT_3)?.objectIds ?: emptyList()
        }
        emit(dto)
    }

    fun getObject(id: Int) = flow {
        val metObjectAdapter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter(MetObjectApiResponseDto::class.java)
        val dto = withContext(dispatcherProvider.default) {
            @Suppress("BlockingMethodInNonBlockingContext")
            metObjectAdapter.fromJson(OBJECT_321412)
        }
        val metObject = dto?.let { MetObjectApiResponseDto.toMetObject(dto) } ?: MetObject.EMPTY
        emit(metObject)
    }
}