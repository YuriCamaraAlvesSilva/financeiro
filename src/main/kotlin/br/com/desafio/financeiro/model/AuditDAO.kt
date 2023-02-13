package br.com.desafio.financeiro.model

import java.sql.Date
import java.time.LocalDate


data class AuditDAO(
        val idCategory: Int? = null,
        val startDate: Date? = Date.valueOf(LocalDate.MIN),
        val endDate: Date? = Date.valueOf(LocalDate.now())
)
