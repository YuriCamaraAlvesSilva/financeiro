package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.component.AccountEntryComponent
import br.com.desafio.financeiro.component.SubCategoryComponent
import br.com.desafio.financeiro.exception.AccountEntryCreateException
import br.com.desafio.financeiro.exception.AccountEntryNotFoundException
import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.repository.AccountEntryRepository
import br.com.desafio.financeiro.repository.customRepository.AccountEntryRepositoryImpl
import org.jboss.logging.Logger
import org.springframework.stereotype.Service

@Service
class AccountEntryService(
        val accountEntryRepository: AccountEntryRepository,
        val accountEntryComponent: AccountEntryComponent,
) {
    val logger: Logger = Logger.getLogger(javaClass.name)

    fun createAccountEntry(accountEntry: AccountEntryEntity): AccountEntryEntity {
        if (!accountEntryComponent.hasSubCategoryWithId(accountEntry.idSubCategory!!)){
            throw AccountEntryCreateException()
        }
        return accountEntryRepository.save(accountEntry)
    }

    fun getAllAccountEntry(): MutableIterable<AccountEntryEntity> {
        logger.info("action=findAllAccountEntry")
        return accountEntryRepository.findAll()
    }

    fun getAccountEntryById(id: Int): AccountEntryEntity {
        return accountEntryRepository.findById(id).orElseThrow { AccountEntryNotFoundException() }
    }

    fun deleteAccountEntry(id: Int) {
        accountEntryRepository.deleteById(id)
    }

    fun updateAccountEntry(accountEntry: AccountEntryEntity): AccountEntryEntity {
        accountEntryRepository.findById(accountEntry.idAccountEntry!!).orElseThrow { AccountEntryNotFoundException() }
        return accountEntryRepository.save(accountEntry)
    }
}