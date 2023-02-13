package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.exception.InvalidApiKeyException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class AuthRequestValidatorComponent(
        @Value("\${api-key}")
        val apiKey: String? = ""
) {
    val logger: Logger = Logger.getLogger(javaClass.name)
    fun validateApiKey(key: String) {
        logger.info("action=ValidatingApiKey")
        if (apiKey != key) {
            logger.info("action=ApiKeyIsInvalid")
            throw InvalidApiKeyException()
        }
    }
}