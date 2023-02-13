package br.com.desafio.financeiro.converter

import br.com.desafio.financeiro.model.AuditDAO
import br.com.desafio.financeiro.model.AuditDTO
import br.com.desafio.financeiro.model.response.AuditResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import java.sql.Date
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class AuditConverterTest {
    @InjectMocks
    lateinit var auditConverter: AuditConverter

    private val date = Date.valueOf(LocalDate.now())

    @Test
    fun `shouldConvertInAuditResponse`() {
        val result = auditConverter.auditResponse(null, buildAuditDTO())
        assertAll(
                { assertEquals(null, result.category) },
                { assertEquals(0.0, result.expenses) },
                { assertEquals(0.0, result.income) },
                { assertEquals(0.0, result.balance) },
        )
    }

    @Test
    fun `shouldConvertRequestToAuditDAO`() {
        val result = auditConverter.requestToAuditDAO(null, date, date)
        assertAll(
                { assertEquals(date, result.endDate) },
                { assertEquals(date, result.startDate) },
                { assertEquals(null, result.idCategory) }
        )
    }

    @Test
    fun `whenPassDateShouldReturnTheSameToStartDate`() {
        val result = auditConverter.getStartDate(date)
        assertEquals(date, result)
    }

    @Test
    fun `whenPassDateShouldReturnTheSameToEndDate`() {
        val result = auditConverter.getEndDate(date)
        assertEquals(date, result)
    }

    @Test
    fun `whenDateWasNotPassedShouldReturnDefaultDate`() {
        val result = auditConverter.getStartDate(null)
        assertEquals(Date.valueOf(LocalDate.of(1000, 1, 1)), result)
    }

    private fun buildAuditDAO() = AuditDAO()
    private fun buildAuditDTO() = AuditDTO(0.0, 0.0, 0.0)
    private fun buildAuditResponse() = AuditResponse(null, 0.0, 0.0, 0.0)
}