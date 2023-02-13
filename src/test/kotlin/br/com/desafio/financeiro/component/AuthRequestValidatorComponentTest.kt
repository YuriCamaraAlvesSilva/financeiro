package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.exception.InvalidApiKeyException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AuthRequestValidatorComponentTest {
    @InjectMocks
    lateinit var authRequestValidatorComponent: AuthRequestValidatorComponent

    @Test
    fun `whenApiKeyIsDifferentThanSystemVariableShouldThrowInvalidApiKeyException`() {
        val key = "test"
        assertThrows<InvalidApiKeyException> {
            authRequestValidatorComponent.validateApiKey(key)
        }
    }

    @Test
    fun `shouldDoNothingWhenApiKeyIsValid`() {
        val key = ""
        assertDoesNotThrow {
            authRequestValidatorComponent.validateApiKey(key)
        }
    }
}