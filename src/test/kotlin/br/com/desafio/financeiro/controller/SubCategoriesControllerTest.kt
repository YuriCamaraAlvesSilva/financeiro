package br.com.desafio.financeiro.controller

import br.com.desafio.financeiro.component.AuthRequestValidatorComponent
import br.com.desafio.financeiro.model.SubCategoriesEntity
import br.com.desafio.financeiro.service.SubCategoryService
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
class SubCategoriesControllerTest {

    @InjectMocks
    lateinit var subCategoriesController: SubCategoriesController

    @Mock
    lateinit var authRequestValidatorComponent: AuthRequestValidatorComponent

    @Mock
    lateinit var subCategoriesService: SubCategoryService

    private val id = 123

    @Test
    fun `shouldGetAccountEntries`() {

        subCategoriesController.getSubCategories("")

        verify(authRequestValidatorComponent, times(1)).validateApiKey("")
        verify(subCategoriesService, times(1)).getAllSubCategories()
        assertEquals(OK, subCategoriesController.getSubCategories("").statusCode)
    }

    @Test
    fun `shouldGetAccountEntry`() {
        subCategoriesController.getSubCategory("", id)
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(subCategoriesService, times(1)).getSubCategoryById(id)
        assertEquals(OK, subCategoriesController.getSubCategory("", id).statusCode)
    }

    @Test
    fun `shouldCreateAccountEntry`() {
        subCategoriesController.createSubCategory("", buildSubCategory())
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(subCategoriesService, times(1)).createSubCategory(buildSubCategory())
        assertEquals(CREATED, subCategoriesController.createSubCategory("", buildSubCategory()).statusCode)
    }

    @Test
    fun `shouldUpdateAccountEntry`() {
        subCategoriesController.updateSubCategory("", buildSubCategory())
        verify(authRequestValidatorComponent, times(1)).validateApiKey("")

        verify(subCategoriesService, times(1)).updateSubCategory(buildSubCategory())
        assertEquals(OK, subCategoriesController.updateSubCategory("", buildSubCategory()).statusCode)
    }

    private fun buildSubCategory() = SubCategoriesEntity()
}