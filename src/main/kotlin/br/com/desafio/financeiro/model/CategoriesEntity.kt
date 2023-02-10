package br.com.desafio.financeiro.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class CategoriesEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val idCategory: Int? = null,
        val name: String = ""
)
