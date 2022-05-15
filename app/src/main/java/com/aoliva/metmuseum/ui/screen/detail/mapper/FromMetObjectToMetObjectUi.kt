package com.aoliva.metmuseum.ui.screen.detail.mapper

import com.aoliva.metmuseum.domain.model.MetObject
import com.aoliva.metmuseum.ui.screen.detail.model.MetObjectUi
import javax.inject.Inject

class FromMetObjectToMetObjectUi @Inject constructor() {

    fun map(unmapped: MetObject) =
        MetObjectUi(
            id = unmapped.id,
            title = unmapped.title,
            author = unmapped.author,
            imageLow = unmapped.imageLow,
            image = unmapped.image
        )
}