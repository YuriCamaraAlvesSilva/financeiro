package br.com.desafio.financeiro.controller

import br.com.desafio.financeiro.component.AuthRequestValidatorComponent
import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.service.AccountEntryService
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus.*


@ExtendWith(MockitoExtension::class)
class AccountEntryControllerTest {

    @InjectMocks
    lateinit var accountEntryController: AccountEntryController

    @Mock
    lateinit var authRequestValidatorComponent: AuthRequestValidatorComponent

    @Mock
    lateinit var accountEntryService: AccountEntryService

    private val id = 123

    @Test
    fun `shouldGetAccountEntries`() {

        accountEntryController.getAccountEntries("")

        verify(authRequestValidatorComponent, times(1)).validateApiKey("")
        verify(accountEntryService, times(1)).getAllAccountEntry()
        assertEquals(OK, accountEntryController.getAccountEntries("").statusCode)
    }

    @Test
    fun `shouldGetAccountEntry`() {
        accountEntryController.getAccountEntry("", id)
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(accountEntryService, times(1)).getAccountEntryById(id)
        assertEquals(OK, accountEntryController.getAccountEntry("", id).statusCode)
    }

    @Test
    fun `shouldCreateAccountEntry`() {
        accountEntryController.createAccountEntry("", buildAccountEntry())
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(accountEntryService, times(1)).createAccountEntry(buildAccountEntry())
        assertEquals(CREATED, accountEntryController.createAccountEntry("", buildAccountEntry()).statusCode)
    }

    @Test
    fun `shouldUpdateAccountEntry`() {
        accountEntryController.updateAccountEntry("", buildAccountEntry())
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(accountEntryService, times(1)).updateAccountEntry(buildAccountEntry())
        assertEquals(OK, accountEntryController.updateAccountEntry("", buildAccountEntry()).statusCode)
    }

    @Test
    fun `shouldDeleteAccountEntry`() {
        accountEntryController.deleteAccountEntry("", id)
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(accountEntryService, times(1)).deleteAccountEntry(id)
    }

    private fun buildAccountEntry() = AccountEntryEntity()
}