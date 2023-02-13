package br.com.desafio.financeiro.controller.advice

import br.com.desafio.financeiro.exception.*
import br.com.desafio.financeiro.model.ErrorHandlingModel
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler
    fun handlerIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
    }

    @ExceptionHandler
    fun handlerCategoryNotFoundException(ex: CategoryNotFoundException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
    }


    @ExceptionHandler
    fun handlerAccountEntryNotFoundException(ex: AccountEntryNotFoundException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
    }


    @ExceptionHandler
    fun handlerSubCategoryNotFoundException(ex: SubCategoryNotFoundException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
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
    fun handlerCategoryAlreadyExistsException(ex: CategoryAlreadyExistsException): ResponseEntity<ErrorHandlingModel> {
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

    @ExceptionHandler
    fun handlerHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                "Os dados informados são invalidos, verifique e tente novamente"
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
    }

    @ExceptionHandler
    fun handlerCategoryCanNotBeDeletedException(ex: CategoryCanNotBeDeletedException): ResponseEntity<ErrorHandlingModel> {
        val errorMessage = ErrorHandlingModel(
                BAD_REQUEST.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, BAD_REQUEST)
    }
}