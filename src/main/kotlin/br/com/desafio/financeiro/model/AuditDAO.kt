package br.com.desafio.financeiro.model

import java.time.LocalDate


data class AuditDAO(
        val idCategory: Int? = null,
        val startDate: LocalDate? = null,
        val endDate: LocalDate? = null
)
