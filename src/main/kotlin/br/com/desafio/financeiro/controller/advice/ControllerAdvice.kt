package br.com.desafio.financeiro.controller.advice

import br.com.desafio.financeiro.exception.AccountEntryNotFoundException
import br.com.desafio.financeiro.exception.CategoryNotFoundException
import br.com.desafio.financeiro.exception.InvalidApiKeyException
import br.com.desafio.financeiro.exception.SubCategoryNotFoundException
import br.com.desafio.financeiro.model.ErrorHandlingModel
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler
    fun handlerIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage= ErrorHandlingModel(
        NOT_FOUND.value(),
        ex.message
        )
        return ResponseEntity(errorMessage, NOT_FOUND)
    }

    @ExceptionHandler
    fun handlerCategoryNotFoundException(ex: CategoryNotFoundException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage= ErrorHandlingModel(
        NOT_FOUND.value(),
        ex.message
        )
        return ResponseEntity(errorMessage, NOT_FOUND)
    }


    @ExceptionHandler
    fun handlerAccountEntryNotFoundException(ex: AccountEntryNotFoundException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage= ErrorHandlingModel(
        NOT_FOUND.value(),
        ex.message
        )
        return ResponseEntity(errorMessage, NOT_FOUND)
    }


    @ExceptionHandler
    fun handlerSubCategoryNotFoundException(ex: SubCategoryNotFoundException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage= ErrorHandlingModel(
        NOT_FOUND.value(),
        ex.message
        )
        return ResponseEntity(errorMessage, NOT_FOUND)
    }


    @ExceptionHandler
    fun handlerMissingRequestHeaderException(ex: MissingRequestHeaderException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage= ErrorHandlingModel(
        UNAUTHORIZED.value(),
        ex.message
        )
        return ResponseEntity(errorMessage, UNAUTHORIZED)
    }


    @ExceptionHandler
    fun handlerInvalidApiKeyException(ex: InvalidApiKeyException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage= ErrorHandlingModel(
        UNAUTHORIZED.value(),
        ex.message
        )
        return ResponseEntity(errorMessage, UNAUTHORIZED)
    }

}