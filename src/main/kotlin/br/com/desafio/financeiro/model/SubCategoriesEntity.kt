package br.com.desafio.financeiro.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class SubCategoriesEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val idSubCategory: Int,
        val name: String,
        val idCategory: String
)
