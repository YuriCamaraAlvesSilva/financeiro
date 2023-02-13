package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.component.CategoryComponent
import br.com.desafio.financeiro.exception.CategoryAlreadyExistsException
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.repository.CategoriesRepository
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
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.*

@ExtendWith(MockitoExtension::class)
class CategoryServiceTest {
    @InjectMocks
    lateinit var categoriesService: CategoriesService

    @Mock
    lateinit var categoriesRepository: CategoriesRepository

    @Mock
    lateinit var categoryComponent: CategoryComponent

    @Mock
    lateinit var subCategoryService: SubCategoryService

    private val id = 123

    @Test
    fun `shouldDeleteCategoryById`() {
        categoriesService.deleteCategory(id)
        verify(subCategoryService, times(1)).deleteAllSubCategoriesByCategoryId(id)
        verify(categoriesRepository, times(1)).deleteById(id)
    }

    @Test
    fun `shouldGetCategory`() {
        whenever(categoryComponent.getCategoryWithId(id)) doReturn buildCategory()
        val result = categoriesService.getCategoryById(id)
        assertAll(
                { assertEquals(123, result.idCategory) },
                { assertEquals("", result.name) },
        )
    }

    @Test
    fun `shouldCreateNewCategory`() {
        whenever(categoryComponent.hasCategoryWithName("")) doReturn false
        whenever(categoriesRepository.save(buildCategory())) doReturn buildCategory()
        val result = categoriesService.createCategory(buildCategory())
        verify(categoriesRepository, times(1)).save(buildCategory())
        assertAll(
                { assertEquals(123, result.idCategory) },
                { assertEquals("", result.name) },
        )
    }

    @Test
    fun `shouldNotCreateNewCategoryBecauseThereIsCategoryWithTheSameName`() {
        whenever(categoryComponent.hasCategoryWithName("")) doReturn true

        assertThrows<CategoryAlreadyExistsException> {
            categoriesService.createCategory(buildCategory())
        }
        verify(categoriesRepository, times(0)).save(buildCategory())
    }

    @Test
    fun `shouldReturnAllCategories`() {
        whenever(categoriesRepository.findAll()) doReturn mutableListOf(buildCategory())
        categoriesService.getAllCategories()
        verify(categoriesRepository, times(1)).findAll()
    }

    @Test
    fun `shouldUpdateCategoryIfItExists`() {
        whenever(categoryComponent.hasCategoryWithName("")) doReturn false
        whenever(categoriesService.getCategoryById(123)) doReturn buildCategory()
        whenever(categoriesRepository.save(buildCategory())) doReturn buildCategory()
        val result = categoriesService.updateCategory(buildCategory())
        verify(categoriesRepository, times(1)).save(buildCategory())
        assertAll(
                { assertEquals(123, result.idCategory) },
                { assertEquals("", result.name) },
        )
    }

    @Test
    fun `shouldNotUpdateCategoryIfThereIsCategoryWithTheSameName`() {
        whenever(categoryComponent.hasCategoryWithName("")) doReturn true
        assertThrows<CategoryAlreadyExistsException> { categoriesService.updateCategory(buildCategory()) }
        verify(categoriesRepository, times(0)).save(buildCategory())

    }

    private fun buildCategory() = CategoriesEntity(123, "")
}