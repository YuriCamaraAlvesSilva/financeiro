package br.com.desafio.financeiro.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class AuditEntity(
        @Id
        val idCategory: Int,
        val startDate: LocalDate,
        val endDate: LocalDate
)
