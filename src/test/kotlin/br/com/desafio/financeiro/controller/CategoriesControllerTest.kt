package br.com.desafio.financeiro.controller

import br.com.desafio.financeiro.component.AuthRequestValidatorComponent
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.service.CategoriesService
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
class CategoriesControllerTest {

    @InjectMocks
    lateinit var categoriesController: CategoriesController

    @Mock
    lateinit var authRequestValidatorComponent: AuthRequestValidatorComponent

    @Mock
    lateinit var categoriesService: CategoriesService

    private val id = 123

    @Test
    fun `shouldGetAccountEntries`() {

        categoriesController.getCategories("")

        verify(authRequestValidatorComponent, times(1)).validateApiKey("")
        verify(categoriesService, times(1)).getAllCategories()
        assertEquals(OK, categoriesController.getCategories("").statusCode)
    }

    @Test
    fun `shouldGetAccountEntry`() {
        categoriesController.getCategory("", id)
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(categoriesService, times(1)).getCategoryById(id)
        assertEquals(OK, categoriesController.getCategory("", id).statusCode)
    }

    @Test
    fun `shouldCreateAccountEntry`() {
        categoriesController.createCategory("", buildCategory())
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(categoriesService, times(1)).createCategory(buildCategory())
        assertEquals(CREATED, categoriesController.createCategory("", buildCategory()).statusCode)
    }

    @Test
    fun `shouldUpdateAccountEntry`() {
        categoriesController.updateCategory("", buildCategory())
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(categoriesService, times(1)).updateCategory(buildCategory())
        assertEquals(OK, categoriesController.updateCategory("", buildCategory()).statusCode)
    }

    @Test
    fun `shouldDeleteAccountEntry`() {
        categoriesController.deleteCategory("", id)
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(categoriesService, times(1)).deleteCategory(id)
    }

    private fun buildCategory() = CategoriesEntity()
}