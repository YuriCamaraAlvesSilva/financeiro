package br.com.desafio.financeiro.converter

import br.com.desafio.financeiro.model.AuditDAO
import br.com.desafio.financeiro.model.AuditDTO
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.model.response.AuditResponse
import org.springframework.stereotype.Component
import java.sql.Date
import java.time.LocalDate

@Component
class AuditConverter {
    fun auditResponse(category: CategoriesEntity?, auditDTO: AuditDTO) = AuditResponse(
            category, auditDTO.credit, auditDTO.debit, auditDTO.balance
    )

    fun requestToAuditDAO(idCategory: Int?, startDate: Date?, endDate: Date?) = AuditDAO(idCategory, getStartDate(startDate), getEndDate(endDate))

    fun getStartDate(startDate: Date?): Date {
        return startDate ?: Date.valueOf(LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY))
    }

    fun getEndDate(endDate: Date?): Date {
        return endDate ?: Date.valueOf(LocalDate.now())
    }

    companion object {
        const val DEFAULT_YEAR = 1000
        const val DEFAULT_MONTH = 1
        const val DEFAULT_DAY = 1

    }
}