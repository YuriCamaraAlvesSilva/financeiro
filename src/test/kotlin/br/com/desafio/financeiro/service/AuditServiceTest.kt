package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.component.AuditComponent
import br.com.desafio.financeiro.component.CategoryComponent
import br.com.desafio.financeiro.converter.AuditConverter
import br.com.desafio.financeiro.model.AuditDAO
import br.com.desafio.financeiro.model.AuditDTO
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.model.response.AuditResponse
import br.com.desafio.financeiro.repository.AccountEntryRepository
import br.com.desafio.financeiro.repository.CategoriesRepository
import br.com.desafio.financeiro.repository.SubCategoriesRepository
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.*

@ExtendWith(MockitoExtension::class)
class AuditServiceTest {
    @InjectMocks
    lateinit var auditService: AuditService

    @Mock
    lateinit var accountEntryRepository: AccountEntryRepository

    @Mock
    lateinit var categoryRepository: CategoriesRepository

    @Mock
    lateinit var categoryComponent: CategoryComponent

    @Mock
    lateinit var subCategoryRepository: SubCategoriesRepository

    @Mock
    lateinit var auditComponent: AuditComponent

    @Mock
    lateinit var auditConverter: AuditConverter

    private val id = 123

    @Test
    fun `shouldDeleteAll`() {
        auditService.deleteAll()
        verify(accountEntryRepository, times(1)).deleteAll()
        verify(categoryRepository, times(1)).deleteAll()
        verify(subCategoryRepository, times(1)).deleteAll()
    }

    @Test
    fun `shouldReturnAuditResponseWithCategoryObject`() {
        whenever(auditComponent.audit(buildAuditDAO())) doReturn buildAuditDTO()
        whenever(auditComponent.hasIdCategory(buildAuditDAO())) doReturn true
        whenever(categoryComponent.getCategoryWithId(123)) doReturn buildCategory()
        whenever(auditConverter.auditResponse(category = buildCategory(), auditDTO = buildAuditDTO())) doReturn buildAuditResponse()
        val result = auditService.audit(buildAuditDAO())
        assertAll(
                { assertEquals(null, result.category?.idCategory) },
                { assertEquals(null, result.category?.name) },
                { assertEquals(0.0, result.balance) },
                { assertEquals(0.0, result.income) },
                { assertEquals(0.0, result.expenses) },
        )
    }

    @Test
    fun `shouldReturnAuditResponseWithoutCategoryObject`() {
        whenever(auditComponent.audit(buildAuditDAO())) doReturn buildAuditDTO()
        whenever(auditComponent.hasIdCategory(buildAuditDAO())) doReturn false
        whenever(auditConverter.auditResponse(category = null, auditDTO = buildAuditDTO())) doReturn buildAuditResponse()
        val result = auditService.audit(buildAuditDAO())
        assertAll(
                { assertEquals(null, result.category?.idCategory) },
                { assertEquals(null, result.category?.name) },
                { assertEquals(0.0, result.balance) },
                { assertEquals(0.0, result.income) },
                { assertEquals(0.0, result.expenses) },
        )
    }

    private fun buildAuditDAO(id: Int? = 123) = AuditDAO(idCategory = id)
    private fun buildAuditDTO() = AuditDTO(0.0, 0.0, 0.0)
    private fun buildCategory() = CategoriesEntity()
    private fun buildAuditResponse(categoriesEntity: CategoriesEntity? = null) = AuditResponse(categoriesEntity, 0.0, 0.0, 0.0)

}