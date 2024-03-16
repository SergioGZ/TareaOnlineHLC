package com.sergio.apitarea4

data class Digimon(
    val id: Int,
    val name: String,
    val href: String,
    val image: String
)

data class DigimonResponse(
    val content: List<Digimon>,
    val pageable: Pageable
)

data class Pageable(
    val currentPage: Int,
    val elementsOnPage: Int,
    val totalElements: Int,
    val totalPages: Int,
    val previousPage: String?,
    val nextPage: String?
)
