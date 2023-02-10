package br.com.desafio.financeiro.model

data class AuditDTO(
        val credit: Double,
        val debit: Double,
        val balance: Double
)