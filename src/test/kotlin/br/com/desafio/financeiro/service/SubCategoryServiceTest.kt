package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.component.CategoryComponent
import br.com.desafio.financeiro.component.SubCategoryComponent
import br.com.desafio.financeiro.exception.SubCategoryCreateException
import br.com.desafio.financeiro.exception.SubCategoryNotFoundException
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.model.SubCategoriesEntity
import br.com.desafio.financeiro.repository.SubCategoriesRepository
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
class SubCategoryServiceTest {
    @InjectMocks
    lateinit var subCategoryService: SubCategoryService

    @Mock
    lateinit var subCategoriesRepository: SubCategoriesRepository

    @Mock
    lateinit var categoryComponent: CategoryComponent

    @Mock
    lateinit var subCategoryComponent: SubCategoryComponent

    private val id = 123

    @Test
    fun `shouldDeleteAllSubCategoriesByIdCategory`() {
        whenever(subCategoryComponent.getIdsToDeleteSubCategoriesFromCategory(id)) doReturn mutableListOf(1, 2, 3)
        subCategoryService.deleteAllSubCategoriesByCategoryId(id)
        verify(subCategoriesRepository, times(1)).deleteAllById(mutableListOf(1, 2, 3))
    }

    @Test
    fun `shouldGetSubCategory`() {
        whenever(subCategoriesRepository.findById(id)) doReturn Optional.of(buildSubCategory())
        val result = subCategoryService.getSubCategoryById(id)
        assertAll(
                { assertEquals(123, result?.idCategory) },
                { assertEquals("", result?.name) },
                { assertEquals(123, result?.idSubCategory) },
        )
    }

    @Test
    fun `shouldCreateNewSubCategory`() {
        whenever(subCategoryComponent.hasSubCategoryWithName("")) doReturn false
        whenever(categoryComponent.getCategoryWithId(buildSubCategory().idCategory!!)) doReturn buildCategory()
        whenever(subCategoriesRepository.save(buildSubCategory())) doReturn buildSubCategory()
        val result = subCategoryService.createSubCategory(buildSubCategory())
        verify(subCategoriesRepository, times(1)).save(buildSubCategory())
        assertAll(
                { assertEquals(123, result.idCategory) },
                { assertEquals(123, result.idSubCategory) },
                { assertEquals("", result.name) },
        )
    }

    @Test
    fun `shouldNotCreateNewSubCategoryBecauseThereIsSubCategoryWithTheSameName`() {
        whenever(subCategoryComponent.hasSubCategoryWithName("")) doReturn true

        assertThrows<SubCategoryCreateException> {
            subCategoryService.createSubCategory(buildSubCategory())
        }
        verify(subCategoriesRepository, times(0)).save(buildSubCategory())
    }

    @Test
    fun `shouldReturnAllSubCategories`() {
        whenever(subCategoriesRepository.findAll()) doReturn mutableListOf(buildSubCategory())
        subCategoryService.getAllSubCategories()
        verify(subCategoriesRepository, times(1)).findAll()
    }

    @Test
    fun `shouldUpdateSubCategoryIfItExists`() {
        whenever(subCategoriesRepository.findById(123)) doReturn Optional.of(buildSubCategory())
        whenever(subCategoriesRepository.save(buildSubCategory())) doReturn buildSubCategory()
        val result = subCategoryService.updateSubCategory(buildSubCategory())
        verify(subCategoriesRepository, times(1)).save(buildSubCategory())
        assertAll(
                { assertEquals(123, result.idCategory) },
                { assertEquals(123, result.idSubCategory) },
                { assertEquals("", result.name) },
        )
    }

    @Test
    fun `shouldNotUpdateSubCategoryIfThereIsNotSubCategory`() {
        whenever(subCategoriesRepository.findById(123)) doThrow SubCategoryNotFoundException()

        assertThrows<SubCategoryNotFoundException> { subCategoryService.updateSubCategory(buildSubCategory()) }
        verify(subCategoriesRepository, times(0)).save(buildSubCategory())
    }

    @Test
    fun `shouldThrowSubCategoryNotFoundExceptionWhenTryToGetItById`() {
        assertThrows<SubCategoryNotFoundException> { subCategoryService.getSubCategoryById(id) }
        verify(subCategoriesRepository, times(1)).findById(id)
    }

    @Test
    fun `shouldThrowSubCategoryNotFoundExceptionWhenTryToUpdate`() {
        assertThrows<SubCategoryNotFoundException> { subCategoryService.updateSubCategory(buildSubCategory()) }
        verify(subCategoriesRepository, times(0)).save(buildSubCategory())
    }

    private fun buildSubCategory() = SubCategoriesEntity(123, "", 123)
    private fun buildCategory() = CategoriesEntity()
}