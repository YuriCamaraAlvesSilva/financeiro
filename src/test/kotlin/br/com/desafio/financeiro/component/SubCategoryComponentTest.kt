package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.exception.CategoryCanNotBeDeletedException
import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.model.SubCategoriesEntity
import br.com.desafio.financeiro.repository.customRepository.SubCategoriesRepositoryImpl
import br.com.desafio.financeiro.service.AccountEntryService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SubCategoryComponentTest {
    @InjectMocks
    lateinit var subCategoryComponent: SubCategoryComponent

    @Mock
    lateinit var subCategoriesRepositoryImpl: SubCategoriesRepositoryImpl

    @Mock
    lateinit var accountEntryService: AccountEntryService

    @Test
    fun `shouldThrowCategoryCanNotBeDeletedException`() {

        assertThrows<CategoryCanNotBeDeletedException> {
            subCategoryComponent.validateIfAccountEntryHasSubCategoryLinked(mutableListOf(buildAccountEntry()), mutableListOf(buildSubCategoriesEntity()))
        }
    }

    @Test
    fun `shouldRetrieveAListOfIdsToBeDeleted`() {
        val result = subCategoryComponent.getIdsToDelete(mutableListOf(buildSubCategoriesEntity()))

        assertEquals(123, result[0])
    }

    private fun buildAccountEntry() = AccountEntryEntity(idSubCategory = 123)
    private fun buildSubCategoriesEntity() = SubCategoriesEntity(idSubCategory = 123)
}