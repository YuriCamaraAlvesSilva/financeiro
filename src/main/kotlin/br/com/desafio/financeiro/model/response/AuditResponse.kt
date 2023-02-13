package br.com.desafio.financeiro.model.response

import br.com.desafio.financeiro.model.CategoriesEntity

data class AuditResponse(
        val category: CategoriesEntity?,
        val income: Double,
        val expenses: Double,
        val balance: Double
)
