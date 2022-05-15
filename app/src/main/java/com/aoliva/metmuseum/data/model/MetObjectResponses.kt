package com.aoliva.metmuseum.data.model

import com.aoliva.metmuseum.domain.model.MetObject
import com.squareup.moshi.Json

/**
 * @see [Met Museum]()https://metmuseum.github.io/) docs for further info
 */
data class MetObjectApiResponseDto(
    /**
     * Gallery number, where available
     * e.g. "131"
     */
    @Json(name = "GalleryNumber")
    val galleryNumber: String?,
    /**
     * Identifying number for each artwork (not always unique)
     * e.g. "67.241"
     */
    @Json(name = "accessionNumber")
    val accessionNumber: String?,
    /**
     * Year the artwork was acquired.
     * e.g. "1921"
     */
    @Json(name = "accessionYear")
    val accessionYear: String?,
    /**
     * An array containing URLs to the additional images of an object in JPEG format
     */
    @Json(name = "additionalImages")
    val additionalImages: List<String>?,
    /**
     * Used to sort artist names alphabetically. Last Name, First Name, Middle Name, Suffix, and
     * Honorific fields, in that order.
     * e.g. "Gogh, Vincent van"
     */
    @Json(name = "artistAlphaSort")
    val artistAlphaSort: String?,
    /**
     * Year the artist was born
     * e.g. "1840"
     */
    @Json(name = "artistBeginDate")
    val artistBeginDate: String?,
    /**
     * Nationality and life dates of an artist, also includes birth and death city when known.
     * e.g. "Dutch, Zundert 1853–1890 Auvers-sur-Oise"
     */
    @Json(name = "artistDisplayBio")
    val artistDisplayBio: String?,
    /**
     * Artist name in the correct order for display
     */
    @Json(name = "artistDisplayName")
    val artistDisplayName: String?,
    /**
     * Year the artist died
     * e.g. "1926"
     */
    @Json(name = "artistEndDate")
    val artistEndDate: String?,
    /**
     * Gender of the artist (currently contains female designations only)
     * e.g. "female"
     */
    @Json(name = "artistGender")
    val artistGender: String?,
    /**
     *  National, geopolitical, cultural, or ethnic origins or affiliation of the creator or
     *  institution that made the artwork
     *  e.g. "Spanish"; "Dutch"; "French, born Romania"
     */
    @Json(name = "artistNationality")
    val artistNationality: String?,
    /**
     * Describes the extent of creation or describes an attribution qualifier to the information
     * given in the artistRole field
     * eg. "In the Style of", "Possibly by", "Written in French by"
     */
    @Json(name = "artistPrefix")
    val artistPrefix: String?,
    /**
     *  Role of the artist related to the type of artwork or object that was created
     *  e.g. "Artist for Painting", "Designer for Dress"
     */
    @Json(name = "artistRole")
    val artistRole: String?,
    /**
     * Used to record complex information that qualifies the role of a constituent, e.g. extent of
     * participation by the Constituent (verso only, and followers)
     * e.g. "verso only"
     */
    @Json(name = "artistSuffix")
    val artistSuffix: String?,
    /**
     * ULAN URL for the artist
     */
    @Json(name = "artistULAN_URL")
    val artistUlanURL: String?,
    /**
     * Wikidata URL for the artist
     */
    @Json(name = "artistWikidata_URL")
    val artistWikidataURL: String?,
    /**
     * City where the artwork was created
     * e.g. "New York", "Paris", "Tokyo"
     */
    @Json(name = "city")
    val city: String?,
    /**
     *  General term describing the artwork type.
     *  e.g. "Basketry", "Ceramics", "Paintings"
     */
    @Json(name = "classification")
    val classification: String?,
    /**
     * An array containing the constituents associated with an object, with the constituent's
     * role, name, ULAN URL, Wikidata URL, and gender, when available (currently contains
     * female designations only).
     * e.g. @see [ConstituentsApiResponseDto]
     */
    @Json(name = "constituents")
    val constituents: List<ConstituentsApiResponseDto>?,
    /**
     *  Country where the artwork was created or found
     *  e.g. "China", "France", "India"
     */
    @Json(name = "country")
    val country: String?,
    /**
     * County where the artwork was created, may sometimes overlap with State
     * e.g. "Orange County", "Staffordshire", "Brooklyn"
     */
    @Json(name = "county")
    val county: String?,
    /**
     * Text acknowledging the source or origin of the artwork and the year the object was acquired
     * by the museum.
     * e.g. "Robert Lehman Collection, 1975"
     */
    @Json(name = "creditLine")
    val creditLine: String?,
    /**
     * Information about the culture, or people from which an object was created
     * eg. "Afghan", "British", "North African"
     */
    @Json(name = "culture")
    val culture: String?,
    /**
     * Indicates The Met's curatorial department responsible for the artwork
     * e.g. "Egyptian Art"
     */
    @Json(name = "department")
    val department: String?,
    /**
     * Size of the artwork or object
     * e.g. "16 x 20 in. (40.6 x 50.8 cm)"
     */
    @Json(name = "dimensions")
    val dimensions: String?,
    /**
     * Size of the artwork or object in centimeters, parsed
     * @see [DimensionsParsedApiResponseDto]
     */
    @Json(name = "dimensionsParsed")
    val dimensionsParsed: List<DimensionsParsedApiResponseDto>?,
    /**
     * Dynasty (a succession of rulers of the same line or family) under which an object was created
     * eg. "Kingdom of Benin", "Dynasty 12"
     */
    @Json(name = "dynasty")
    val dynasty: String?,
    /**
     *  The name of an excavation. The excavation field usually includes dates of excavation.
     *  e.g. "MMA excavations, 1923–24"; "Khashaba excavations, 1910–11"; "Carnarvon excavations,
     *  1912"
     */
    @Json(name = "excavation")
    val excavation: String?,
    /**
     *  Qualifying information that describes the relationship of the place catalogued in the
     *  geography fields to the object that is being catalogued
     *  e.g. "Made in", "From", "Attributed to"
     */
    @Json(name = "geographyType")
    val geographyType: String?,
    /**
     * When "true" indicates a popular and important artwork in the collection
     */
    @Json(name = "isHighlight")
    val isHighlight: Boolean?,
    /**
     * When "true" indicates an artwork in the Public Domain
     */
    @Json(name = "isPublicDomain")
    val isPublicDomain: Boolean?,
    /**
     * Whether the object is on the Timeline of Art History website
     */
    @Json(name = "isTimelineWork")
    val isTimelineWork: Boolean?,
    /**
     * URL to object's page on metmuseum.org
     */
    @Json(name = "linkResource")
    val linkResource: String?,
    /**
     * Geographic location more specific than subregion, but more specific than locus, where the
     * artwork was found (frequently null)
     * e.g. "Tomb of Perneb", "Temple of Hatshepsut", "Palace of Ramesses II"
     */
    @Json(name = "locale")
    val locale: String?,
    /**
     *  Geographic location that is less specific than locale, but more specific than excavation,
     *  where the artwork was found (frequently null)
     *  e.g. "1st chamber W. wall"; "Burial C 2, In coffin"; "Pit 477"
     */
    @Json(name = "locus")
    val locus: String?,
    /**
     * Array of elements, each with a name, description, and set of measurements. Spatial
     * measurements are in centimeters; weights are in kg.
     * @see [MeasurementApiResponseDto]
     */
    @Json(name = "measurements")
    val measurements: List<MeasurementApiResponseDto>?,
    /**
     *  Refers to the materials that were used to create the artwork
     *  e.g. "Oil on canvas", "Watercolor", "Gold"
     */
    @Json(name = "medium")
    val medium: String?,
    /**
     *  Date metadata was last updated
     *  e.g. 2018-10-17T10:24:43.197Z
     */
    @Json(name = "metadataDate")
    val metadataDate: String?,
    /**
     *  Machine readable date indicating the year the artwork was started to be created
     *  e.g. 1867, 1100, -900
     */
    @Json(name = "objectBeginDate")
    val objectBeginDate: Int?,
    /**
     *  Year, a span of years, or a phrase that describes the specific or approximate date when an
     *  artwork was designed or created
     *  e.g. "1865–67", "19th century", "ca. 1796"
     */
    @Json(name = "objectDate")
    val objectDate: String?,
    /**
     * Machine readable date indicating the year the artwork was completed (may be the same year or
     * different year than the objectBeginDate)
     * e.g. 1888, 1100, -850
     */
    @Json(name = "objectEndDate")
    val objectEndDate: Int?,
    /**
     * Identifying number for each artwork (unique, can be used as key field)
     */
    @Json(name = "objectID")
    val objectID: Int?,
    /**
     * Describes the physical type of the object
     */
    @Json(name = "objectName")
    val objectName: String?,
    /**
     * URL to object's page on metmuseum.org
     * e.g. "https://www.metmuseum.org/art/collection/search/547802"
     */
    @Json(name = "objectURL")
    val objectURL: String?,
    /**
     * Wikidata URL for the object
     */
    @Json(name = "objectWikidata_URL")
    val objectWikidata_URL: String?,
    /**
     * Time or time period when an object was created
     * eg. "Ming dynasty (1368-1644)", "Middle Bronze Age"
     */
    @Json(name = "period")
    val period: String?,
    /**
     * A set of works created as a group or published as a series.
     * eg. "Birds of America", "The Hudson River Portfolio", "Speculum Romanae Magnificentiae"
     */
    @Json(name = "portfolio")
    val portfolio: String?,
    /**
     * URL to the primary image of an object in JPEG format
     */
    @Json(name = "primaryImage")
    val primaryImage: String?,
    /**
     * URL to the lower-res primary image of an object in JPEG format
     */
    @Json(name = "primaryImageSmall")
    val primaryImageSmall: String?,
    /**
     * Geographic location more specific than country, but more specific than subregion, where the
     * artwork was created or found (frequently null)
     * e.g. "Bohemia", "Midwest", "Southern"
     */
    @Json(name = "region")
    val region: String?,
    /**
     * Reign of a monarch or ruler under which an object was created
     * eg. "Amenhotep III", "Darius I", "Louis XVI"
     */
    @Json(name = "reign")
    val reign: String?,
    /**
     * e.g. "Metropolitan Museum of Art, New York, NY"
     */
    @Json(name = "repository")
    val repository: String?,
    /**
     *  Credit line for artworks still under copyright.
     *  e.g. "© 2018 Estate of Pablo Picasso / Artists Rights Society (ARS), New York"
     */
    @Json(name = "rightsAndReproduction")
    val rightsAndReproduction: String?,
    /**
     * River is a natural watercourse, usually freshwater, flowing toward an ocean, a lake, a sea
     * or another river related to the origins of an artwork (frequently null)
     * e.g. "Mississippi River", "Nile River", "River Thames"
     */
    @Json(name = "river")
    val river: String?,
    /**
     * State or province where the artwork was created, may sometimes overlap with County
     * e.g. "Alamance", "Derbyshire", "Brooklyn"
     */
    @Json(name = "state")
    val state: String?,
    /**
     *  Geographic location more specific than Region, but less specific than Locale, where the
     *  artwork was created or found (frequently null)
     *  e.g. "Malqata", "Deir el-Bahri", "Valley of the Kings"
     */
    @Json(name = "subregion")
    val subregion: String?,
    /**
     *  An array of subject keyword tags associated with the object and their respective AAT URL
     *  @see [TagApiResponseDto]
     */
    @Json(name = "tags")
    val tags: List<TagApiResponseDto>?,
    /**
     *  Title, identifying phrase, or name given to a work of art
     */
    @Json(name = "title")
    val title: String?
) {
    companion object {
        fun toMetObject(unmapped: MetObjectApiResponseDto) =
            MetObject(
                id = unmapped.objectID?.toString() ?: "",
                title = unmapped.title ?: "",
                author = unmapped.artistDisplayName,
                imageLow = unmapped.primaryImageSmall,
                image = unmapped.primaryImage
            )
    }
}

/**
 * e.g. [{"term": "Abstraction","AAT_URL": "http://vocab.getty.edu/page/aat/300056508",
 * "Wikidata_URL": "https://www.wikidata.org/wiki/Q162150"}]
 * @see [Met Museum]()https://metmuseum.github.io/) docs for further info
 */
data class TagApiResponseDto(
    @Json(name = "AAT_URL")
    val artArchitectureThesaurusUrl: String?,
    @Json(name = "Wikidata_URL")
    val wikidataUrl: String?,
    @Json(name = "term")
    val term: String?,
)

/**
 * e.g. [ { "elementName": "Overall", "elementDescription": "Temple proper", "elementMeasurements":
 * { "Height": 640.0813, "Length": 1249.6825, "Width": 640.0813 } } ]
 * @see [Met Museum]()https://metmuseum.github.io/) docs for further info
 */
data class MeasurementApiResponseDto(
    @Json(name = "elementDescription")
    val elementDescription: String?,
    @Json(name = "elementMeasurements")
    val elementMeasurements: ElementMeasurementsApiResponseDto?,
    @Json(name = "elementName")
    val elementName: String?,
)

/**
 * @see [Met Museum]()https://metmuseum.github.io/) docs for further info
 */
data class ElementMeasurementsApiResponseDto(
    @Json(name = "Height")
    val height: Double?,
    @Json(name = "Length")
    val length: Double?,
    @Json(name = "Width")
    val width: Double?,
)

/**
 * e.g. [{"element":"Sheet","dimensionType":"Height","dimension":51},{"element":"Plate",
 * "dimensionType":"Height","dimension":47.5},{"element":"Sheet","dimensionType":"Width",
 * "dimension":72.8},{"element":"Plate","dimensionType":"Width","dimension":62.5}]
 * @see [Met Museum]()https://metmuseum.github.io/) docs for further info
 */
data class DimensionsParsedApiResponseDto(
    @Json(name = "element")
    val element: String?,
    @Json(name = "dimensionType")
    val dimensionType: String?,
    @Json(name = "dimension")
    val dimension: Double?
)

/**
 * e.g. [{"constituentID": 161708,"role": "Artist","name": "Louise Bourgeois","constituentULAN_URL":
 * "http://vocab.getty.edu/page/ulan/500057350","constituentWikidata_URL":
 * "https://www.wikidata.org/wiki/Q159409","gender": "Female"}]
 * @see [Met Museum]()https://metmuseum.github.io/) docs for further info
 */
data class ConstituentsApiResponseDto(
    @Json(name = "constituentID")
    val id: Int?,
    @Json(name = "role")
    val role: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "constituentULAN_URL")
    val constituentUlanUrl: String?,
    @Json(name = "gender")
    val gender: String?
)
