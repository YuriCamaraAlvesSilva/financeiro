package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.model.AuditDAO
import br.com.desafio.financeiro.model.AuditDTO
import br.com.desafio.financeiro.model.SubCategoriesEntity
import br.com.desafio.financeiro.repository.AccountEntryRepository
import br.com.desafio.financeiro.repository.CategoriesRepository
import br.com.desafio.financeiro.repository.SubCategoriesRepository
import br.com.desafio.financeiro.repository.customRepository.AccountEntryRepositoryImpl
import br.com.desafio.financeiro.repository.customRepository.SubCategoriesRepositoryImpl
import okhttp3.internal.wait
import org.springframework.stereotype.Service

@Service
class AuditService(
        val subCategoriesRepositoryImpl: SubCategoriesRepositoryImpl,
        val accountEntryRepository: AccountEntryRepository,
        val accountEntryRepositoryImpl: AccountEntryRepositoryImpl,
        val categoryRepository: CategoriesRepository,
        val subCategoryRepository: SubCategoriesRepository,
) {
    fun audit(auditDAO: AuditDAO): AuditDTO {
        val accountEntries: MutableList<AccountEntryEntity> = accountEntryRepositoryImpl.findByAccountEntriesByDate(auditDAO.startDate, auditDAO.endDate)
        return if (auditDAO.idCategory != null) {
            val subCategories: MutableList<SubCategoriesEntity> = subCategoriesRepositoryImpl.findAllByIdCategory(auditDAO.idCategory)
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
            getBalance(filteredAccountEntries)
        } else {
            getBalance(accountEntries)
        }
    }

    fun getBalance(accountEntries: MutableList<AccountEntryEntity>): AuditDTO {
        var credit: Double = 0.0
        var debit: Double = 0.0
        accountEntries.forEach { accountEntry ->
            run {
                if (accountEntry.value >= 0.0) {
                    credit += accountEntry.value
                } else {
                    debit -= accountEntry.value
                }
            }
        }.wait()

        val balance = credit - debit
        return AuditDTO(credit, debit, balance)
    }

    fun deleteAll() {
        accountEntryRepository.deleteAll()
        categoryRepository.deleteAll()
        subCategoryRepository.deleteAll()
    }
}