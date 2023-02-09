package br.com.desafio.financeiro.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@Entity
data class AccountEntryEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val idAccountEntry: Int,
        val value: Double,
        @DateTimeFormat()
        val date: LocalDate,
        val idSubCategory: Int,
        val comment: String?
)
