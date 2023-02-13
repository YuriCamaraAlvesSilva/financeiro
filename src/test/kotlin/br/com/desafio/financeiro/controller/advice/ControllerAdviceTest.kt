package br.com.desafio.financeiro.controller.advice

import br.com.desafio.financeiro.exception.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus.*
import org.springframework.http.converter.HttpMessageNotReadableException

@ExtendWith(MockitoExtension::class)
class ControllerAdviceTest {
    @InjectMocks
    lateinit var controllerAdvice: ControllerAdvice

    @Test
    fun `shouldHandleWhenIllegalStateException`() {
        val result = controllerAdvice.handlerIllegalStateException(IllegalStateException())
        assertEquals(BAD_REQUEST, result.statusCode)
    }

    @Test
    fun `shouldHandleWhenCategoryNotFoundException`() {
        val result = controllerAdvice.handlerCategoryNotFoundException(CategoryNotFoundException())
        assertEquals(BAD_REQUEST, result.statusCode)
    }

    @Test
    fun `shouldHandleWhenAccountEntryNotFoundException`() {
        val result = controllerAdvice.handlerAccountEntryNotFoundException(AccountEntryNotFoundException())
        assertEquals(BAD_REQUEST, result.statusCode)
    }

    @Test
    fun `shouldHandleWhenSubCategoryNotFoundException`() {
        val result = controllerAdvice.handlerSubCategoryNotFoundException(SubCategoryNotFoundException())
        assertEquals(BAD_REQUEST, result.statusCode)
    }


    @Test
    fun `shouldHandleWhenInvalidApiKeyException`() {
        val result = controllerAdvice.handlerInvalidApiKeyException(InvalidApiKeyException())
        assertEquals(UNAUTHORIZED, result.statusCode)
    }


    @Test
    fun `shouldHandleWhenCategoryAlreadyExistsException`() {
        val result = controllerAdvice.handlerCategoryAlreadyExistsException(CategoryAlreadyExistsException())
        assertEquals(BAD_REQUEST, result.statusCode)
    }

    @Test
    fun `shouldHandleWhenCategoryCanNotBeDeletedException`() {
        val result = controllerAdvice.handlerCategoryCanNotBeDeletedException(CategoryCanNotBeDeletedException())
        assertEquals(BAD_REQUEST, result.statusCode)
    }

    @Test
    fun `shouldHandleWhenSubCategoryCreateException`() {
        val result = controllerAdvice.handlerSubCategoryCreateException(SubCategoryCreateException())
        assertEquals(BAD_REQUEST, result.statusCode)
    }

    @Test
    fun `shouldHandleWhenAccountEntryCreateException`() {
        val result = controllerAdvice.handlerAccountEntryException(AccountEntryCreateException())
        assertEquals(BAD_REQUEST, result.statusCode)
    }

    @Test
    fun `shouldHandleWhenHttpMessageNotReadableException`() {
        val result = controllerAdvice.handlerHttpMessageNotReadableException(HttpMessageNotReadableException(""))
        assertEquals(BAD_REQUEST, result.statusCode)
    }

}