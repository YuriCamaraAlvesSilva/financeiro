package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.model.AuditDAO
import br.com.desafio.financeiro.model.AuditDTO
import br.com.desafio.financeiro.repository.SubCategoriesRepository
import org.springframework.stereotype.Service

@Service
class AuditService(
        val accountEntryService: AccountEntryService,
        val subCategoriesRepository: SubCategoriesRepository,
        val categoryService: SubCategoryService
) {
    fun getBalance(auditDAO: AuditDAO): AuditDTO {
        val accountEntryEntity: AccountEntryEntity = accountEntryService.getAccountEntryById(auditDAO.idCategory!!)
        var credit: Double = 0.0
        var debit: Double = 0.0

        if (accountEntryEntity.value >= 0.0) {
            credit += accountEntryEntity.value
        } else {
            debit -= accountEntryEntity.value
        }
        val balance = credit - debit
        return AuditDTO(credit, debit, balance)

    }
}