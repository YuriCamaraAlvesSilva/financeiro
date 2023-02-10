package br.com.desafio.financeiro.model

data class ErrorHandlingModel(
        var status: Int? = null,
        var message: String? = null
)
