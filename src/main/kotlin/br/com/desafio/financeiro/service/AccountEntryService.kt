package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.exception.AccountEntryNotFoundException
import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.repository.AccountEntryRepository
import org.springframework.stereotype.Service

@Service
class AccountEntryService(
        val accountEntryRepository: AccountEntryRepository,
        val subCategoryService: SubCategoryService
) {
    fun createAccountEntry(accountEntry: AccountEntryEntity) {
        subCategoryService.getSubCategoryById(accountEntry.idSubCategory!!)
        accountEntryRepository.save(accountEntry)
    }

    fun getAllAccountEntry(): MutableIterable<AccountEntryEntity> {
        return accountEntryRepository.findAll()
    }

    fun getAccountEntryById(id: Int): AccountEntryEntity {
        return accountEntryRepository.findById(id).orElseThrow { AccountEntryNotFoundException() }
    }

    fun deleteAccountEntry(id: Int) {
        accountEntryRepository.deleteById(id)
    }

    fun updateAccountEntry(accountEntry: AccountEntryEntity) {
        accountEntryRepository.findById(accountEntry.idAccountEntry!!).orElseThrow { AccountEntryNotFoundException() }
        accountEntryRepository.save(accountEntry)
    }
}