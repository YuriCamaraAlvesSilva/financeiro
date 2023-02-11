package br.com.desafio.financeiro.model

import java.sql.Date


data class AuditDAO(
        val idCategory: Int? = null,
        val startDate: Date,
        val endDate: Date
)
