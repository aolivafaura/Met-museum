package com.aoliva.metmuseum.ui.screen.detail.mapper

import com.aoliva.metmuseum.domain.model.MetObject
import com.aoliva.metmuseum.ui.screen.detail.model.MetObjectDetailUi
import javax.inject.Inject

class FromMetObjectToMetObjectUi @Inject constructor() {

    fun map(unmapped: MetObject) =
        MetObjectDetailUi(
            id = unmapped.id,
            title = unmapped.title,
            author = unmapped.author,
            image = unmapped.image
        )
}