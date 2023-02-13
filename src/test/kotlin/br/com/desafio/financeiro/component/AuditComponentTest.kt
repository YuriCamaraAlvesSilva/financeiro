package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.model.AuditDAO
import br.com.desafio.financeiro.model.AuditDTO
import br.com.desafio.financeiro.model.SubCategoriesEntity
import br.com.desafio.financeiro.repository.customRepository.AccountEntryRepositoryImpl
import br.com.desafio.financeiro.repository.customRepository.SubCategoriesRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.sql.Date

@ExtendWith(MockitoExtension::class)
class AuditComponentTest {
    @InjectMocks
    lateinit var auditComponent: AuditComponent

    @Mock
    lateinit var accountEntryRepositoryImpl: AccountEntryRepositoryImpl

    @Mock
    lateinit var subCategoriesRepositoryImpl: SubCategoriesRepositoryImpl


    @Test
    fun `shouldReturnFalseWhenIdCategoryIsNotPresent`() {
        assertEquals(false, auditComponent.hasIdCategory(buildAuditDAO()))
    }

    @Test
    fun `shouldReturnTrueWhenIdCategoryIsPresent`() {
        assertEquals(true, auditComponent.hasIdCategory(buildAuditDAO(idCategory = 123)))
    }

    @Test
    fun `shouldCalculateAndReturnAuditDTO`() {
        val result = auditComponent.getBalance(mutableListOf(buildAccountEntry()))
        assertAll(
                { assertEquals(0.0, result.balance) },
                { assertEquals(0.0, result.credit) },
                { assertEquals(0.0, result.debit) },
        )

    }

    @Test
    fun `shouldFilterAccountEntriesByCategory`() {
        val result = auditComponent.filterAccountEntriesByCategory(mutableListOf(buildAccountEntry()), mutableListOf(buildSubCategories()))
        assertAll(
                { assertEquals(null, result[0].idSubCategory) },
                { assertEquals(null, result[0].idAccountEntry) },
                { assertEquals(0.0, result[0].value) },
                { assertEquals(null, result[0].comment) },
        )

    }

    private fun buildAuditDAO(idCategory: Int? = null, startDate: Date? = null, endDate: Date? = null) = AuditDAO(
            idCategory, startDate, endDate
    )

    private fun buildAuditDTO() = AuditDTO(0.0, 0.0, 0.0)

    private fun buildAccountEntry() = AccountEntryEntity()
    private fun buildSubCategories() = SubCategoriesEntity()

}