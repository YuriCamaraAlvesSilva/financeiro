package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.exception.CategoryNotFoundException
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.repository.CategoriesRepository
import br.com.desafio.financeiro.repository.customRepository.CategoriesRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import java.util.*

@ExtendWith(MockitoExtension::class)
class CategoryComponentTest {
    @InjectMocks
    lateinit var categoryComponent: CategoryComponent

    @Mock
    lateinit var categoriesRepositoryImpl: CategoriesRepositoryImpl

    @Mock
    lateinit var categoriesRepository: CategoriesRepository

    private val id = 123

    @Test
    fun `shouldReturnCategory`() {
        whenever(categoriesRepository.findById(id)) doReturn Optional.of(buildCategory())
        val result = categoryComponent.getCategoryWithId(id)
        assertAll(
                { assertEquals(id, result.idCategory) },
                { assertEquals("test", result.name) }
        )

    }

    @Test
    fun `shouldThrowCategoryNotFoundExceptionWhenCannotFindCategoryById`() {
        whenever(categoriesRepository.findById(id)) doThrow CategoryNotFoundException()
        assertThrows<CategoryNotFoundException> {
            categoryComponent.getCategoryWithId(id)
        }
    }

    @Test
    fun `shouldThrowCategoryNotFoundExceptionWhenCantFindCategoryById`() {
        assertThrows<CategoryNotFoundException> {
            categoryComponent.getCategoryWithId(id)
        }
    }

    private fun buildCategory() = CategoriesEntity(123, "test")
}