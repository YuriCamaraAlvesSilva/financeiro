package br.com.desafio.financeiro.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.sql.Date
import java.time.LocalDate

@Entity
data class AccountEntryEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val idAccountEntry: Int? = null,
        val value: Double = 0.0,
        val date: Date = Date.valueOf(LocalDate.now()),
        val idSubCategory: Int? = null,
        val comment: String? = null
)
