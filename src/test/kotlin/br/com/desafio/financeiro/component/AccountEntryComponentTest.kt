package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.repository.SubCategoriesRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class AccountEntryComponentTest {
    @InjectMocks
    lateinit var accountEntryComponent: AccountEntryComponent

    @Mock
    lateinit var subCategoriesRepository: SubCategoriesRepository


    @Test
    fun `whenSubCategoryExistsWithIdShouldReturnTrue`() {
        val subCategoryId = 123
        whenever(subCategoriesRepository.existsById(subCategoryId)) doReturn true
        assertEquals(true, accountEntryComponent.hasSubCategoryWithId(subCategoryId))
    }

    @Test
    fun `whenSubCategoryNotExistsWithIdShouldReturnFalse`() {
        val subCategoryId = 123
        whenever(subCategoriesRepository.existsById(subCategoryId)) doReturn false
        assertEquals(false, accountEntryComponent.hasSubCategoryWithId(subCategoryId))
    }
}