package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.component.AccountEntryComponent
import br.com.desafio.financeiro.exception.AccountEntryCreateException
import br.com.desafio.financeiro.exception.AccountEntryNotFoundException
import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.repository.AccountEntryRepository
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.*

@ExtendWith(MockitoExtension::class)
class AccountEntryServiceTest {
    @InjectMocks
    lateinit var accountEntryService: AccountEntryService

    @Mock
    lateinit var accountEntryRepository: AccountEntryRepository

    @Mock
    lateinit var accountEntryComponent: AccountEntryComponent

    private val id = 123

    @Test
    fun `shouldDeleteAccountEntryById`() {
        accountEntryService.deleteAccountEntry(id)
        verify(accountEntryRepository, times(1)).deleteById(id)
    }

    @Test
    fun `shouldThrowsAccountEntryNotFoundExceptionWhenCannotFindAccountEntry`() {
        whenever(accountEntryRepository.findById(id)) doThrow AccountEntryNotFoundException()
        assertThrows<AccountEntryNotFoundException> {
            accountEntryService.getAccountEntryById(id)
        }
    }

    @Test
    fun `shouldGetAccountEntry`() {
        whenever(accountEntryRepository.findById(id)) doReturn Optional.of(buildAccountEntry())
        val result = accountEntryService.getAccountEntryById(id)
        verify(accountEntryRepository, times(1)).findById(id)
        assertAll(
                { assertEquals(0.0, result.value) },
                { assertEquals(null, result.comment) },
                { assertEquals(null, result.idAccountEntry) },
                { assertEquals(null, result.idSubCategory) }
        )
    }

    @Test
    fun `shouldCreateNewAccountEntry`() {
        whenever(accountEntryComponent.hasSubCategoryWithId(id)) doReturn true
        whenever(accountEntryRepository.save(buildAccountEntry(id))) doReturn buildAccountEntry(id)
        val result = accountEntryService.createAccountEntry(buildAccountEntry(id))
        verify(accountEntryRepository, times(1)).save(buildAccountEntry(id))
        assertAll(
                { assertEquals(0.0, result.value) },
                { assertEquals(null, result.comment) },
                { assertEquals(null, result.idAccountEntry) },
                { assertEquals(id, result.idSubCategory) }
        )
    }

    @Test
    fun `shouldNotCreateNewAccountEntryBecauseThereIsNotSubCategory`() {
        whenever(accountEntryComponent.hasSubCategoryWithId(id)) doReturn false

        assertThrows<AccountEntryCreateException> {
            accountEntryService.createAccountEntry(buildAccountEntry(id))
        }
        verify(accountEntryRepository, times(0)).save(buildAccountEntry(id))
    }

    @Test
    fun `shouldReturnAllAccountEntries`() {
        whenever(accountEntryRepository.findAll()) doReturn mutableListOf(buildAccountEntry())
        accountEntryService.getAllAccountEntry()
        verify(accountEntryRepository, times(1)).findAll()
    }

    @Test
    fun `shouldUpdateAccountEntryIfItExists`() {
        whenever(accountEntryRepository.findById(id)) doReturn Optional.of(buildAccountEntry(id, id))
        whenever(accountEntryRepository.save(buildAccountEntry(id, id))) doReturn buildAccountEntry(id, id)
        val result = accountEntryService.updateAccountEntry(buildAccountEntry(id, id))
        verify(accountEntryRepository, times(1)).findById(id)
        verify(accountEntryRepository, times(1)).save(buildAccountEntry(id, id))
        assertAll(
                { assertEquals(0.0, result.value) },
                { assertEquals(null, result.comment) },
                { assertEquals(123, result.idAccountEntry) },
                { assertEquals(123, result.idSubCategory) }
        )
    }

    @Test
    fun `shouldNotUpdateAccountEntryWhenItNotExists`() {
        whenever(accountEntryRepository.findById(id)) doThrow AccountEntryNotFoundException()
        assertThrows<AccountEntryNotFoundException> { accountEntryService.updateAccountEntry(buildAccountEntry(id, id)) }
        verify(accountEntryRepository, times(1)).findById(id)
        verify(accountEntryRepository, times(0)).save(buildAccountEntry(id, id))

    }

    @Test
    fun `shouldThrowAccountEntryNotFoundExceptionWhenTryToUpdate`() {
        assertThrows<AccountEntryNotFoundException> { accountEntryService.updateAccountEntry(buildAccountEntry(id, id)) }
        verify(accountEntryRepository, times(1)).findById(id)
        verify(accountEntryRepository, times(0)).save(buildAccountEntry(id, id))

    }

    @Test
    fun `shouldThrowAccountEntryNotFoundExceptionWhenTryToGetById`() {
        assertThrows<AccountEntryNotFoundException> { accountEntryService.getAccountEntryById(id) }
        verify(accountEntryRepository, times(1)).findById(id)
    }

    private fun buildAccountEntry(subCategoryId: Int? = null, idAccountEntry: Int? = null) = AccountEntryEntity(idSubCategory = subCategoryId, idAccountEntry = idAccountEntry)
}