package com.aoliva.metmuseum.ui.screen.department.mapper

import com.aoliva.metmuseum.domain.model.MetObject
import com.aoliva.metmuseum.ui.screen.department.model.MetObjectListItemUi
import javax.inject.Inject

class FromMetObjectToMetObjectListItemUi @Inject constructor() {

    fun map(unmapped: List<MetObject>) =
        unmapped.map { metObject ->
            MetObjectListItemUi(
                id = metObject.id,
                title = metObject.title,
                author = metObject.author,
                image = metObject.imageLow
            )
        }
}