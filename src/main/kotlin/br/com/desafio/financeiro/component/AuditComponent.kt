package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.model.AuditDAO
import br.com.desafio.financeiro.model.AuditDTO
import br.com.desafio.financeiro.model.SubCategoriesEntity
import br.com.desafio.financeiro.repository.customRepository.AccountEntryRepositoryImpl
import br.com.desafio.financeiro.repository.customRepository.SubCategoriesRepositoryImpl
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class AuditComponent(
        val accountEntryRepositoryImpl: AccountEntryRepositoryImpl,
        val subCategoriesRepositoryImpl: SubCategoriesRepositoryImpl
) {
    val logger: Logger = Logger.getLogger(javaClass.name)
    fun audit(auditDAO: AuditDAO): AuditDTO {
        logger.info("action=SearchingAccountEntriesByDate")
        val accountEntries: MutableList<AccountEntryEntity> = accountEntryRepositoryImpl.findByAccountEntriesByDate(auditDAO.startDate!!, auditDAO.endDate!!)
        return if (hasIdCategory(auditDAO)) {
            logger.info("action=SearchingSubCategoriesLinkedByAccountEntries")
            val subCategories: MutableList<SubCategoriesEntity> = subCategoriesRepositoryImpl.findAllByIdCategory(auditDAO.idCategory!!)
            val filteredAccountEntries = filterAccountEntriesByCategory(accountEntries, subCategories)
            getBalance(filteredAccountEntries)
        } else {
            getBalance(accountEntries)
        }
    }

    fun getBalance(accountEntries: MutableList<AccountEntryEntity>): AuditDTO {
        var credit: Double = 0.0
        var debit: Double = 0.0
        logger.info("action=CalculatingIncomeAndExpensesFromAccountEntries")
        accountEntries.forEach { accountEntry ->
            run {
                if (accountEntry.value >= 0.0) {
                    credit += accountEntry.value
                } else {
                    debit -= accountEntry.value
                }
            }
        }
        logger.info("action=CalculatingBalanceFromIncomeAndExpenses")
        val balance = credit - debit
        return AuditDTO(credit, debit, balance)
    }

    fun hasIdCategory(auditDAO: AuditDAO): Boolean {
        logger.info("action=ValidatingIfIdCategoryIsNotNull")
        return auditDAO.idCategory != null
    }

    fun filterAccountEntriesByCategory(
            accountEntries: MutableList<AccountEntryEntity>,
            subCategories: MutableList<SubCategoriesEntity>
    ): MutableList<AccountEntryEntity> {
        logger.info("action=FilteringAccountEntriesLinkedBySubCategories")
        val filteredAccountEntries: MutableList<AccountEntryEntity> = mutableListOf()
        accountEntries.forEach { accountEntry ->
            run {
                subCategories.forEach { subCategory ->
                    run {
                        if (subCategory.idSubCategory == accountEntry.idSubCategory) {
                            filteredAccountEntries.add(accountEntry)
                        }
                    }
                }
            }
        }
        return filteredAccountEntries
    }
}