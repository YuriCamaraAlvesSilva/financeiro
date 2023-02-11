package br.com.desafio.financeiro.controller.advice

import br.com.desafio.financeiro.exception.*
import br.com.desafio.financeiro.model.ErrorHandlingModel
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler
    fun handlerIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                NOT_FOUND.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, NOT_FOUND)
    }

    @ExceptionHandler
    fun handlerCategoryNotFoundException(ex: CategoryNotFoundException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                NOT_FOUND.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, NOT_FOUND)
    }


    @ExceptionHandler
    fun handlerAccountEntryNotFoundException(ex: AccountEntryNotFoundException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                NOT_FOUND.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, NOT_FOUND)
    }


    @ExceptionHandler
    fun handlerSubCategoryNotFoundException(ex: SubCategoryNotFoundException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                NOT_FOUND.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, NOT_FOUND)
    }


    @ExceptionHandler
    fun handlerMissingRequestHeaderException(ex: MissingRequestHeaderException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                UNAUTHORIZED.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, UNAUTHORIZED)
    }


    @ExceptionHandler
    fun handlerInvalidApiKeyException(ex: InvalidApiKeyException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                UNAUTHORIZED.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, UNAUTHORIZED)
    }

    @ExceptionHandler
    fun handlerEmptyResultDataAccessException(ex: EmptyResultDataAccessException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                "Não foi possivel completar a operação, verifique os dados informados e tente novamente"
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
    }

    @ExceptionHandler
    fun handlerCategoryCreateException(ex: CategoryCreateException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
    }

    @ExceptionHandler
    fun handlerSubCategoryCreateException(ex: SubCategoryCreateException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
    }

    @ExceptionHandler
    fun handlerAccountEntryException(ex: AccountEntryCreateException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
    }
}