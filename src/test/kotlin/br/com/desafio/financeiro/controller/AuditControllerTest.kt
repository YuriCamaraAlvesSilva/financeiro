package br.com.desafio.financeiro.controller

import br.com.desafio.financeiro.component.AuthRequestValidatorComponent
import br.com.desafio.financeiro.converter.AuditConverter
import br.com.desafio.financeiro.model.AuditDAO
import br.com.desafio.financeiro.service.AuditService
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.springframework.http.HttpStatus.*
import java.sql.Date
import java.time.LocalDate


@ExtendWith(MockitoExtension::class)
class AuditControllerTest {

    @InjectMocks
    lateinit var auditController: AuditController

    @Mock
    lateinit var authRequestValidatorComponent: AuthRequestValidatorComponent

    @Mock
    lateinit var auditService: AuditService

    @Mock
    lateinit var auditConverter: AuditConverter
    private val date: Date = Date.valueOf(LocalDate.now())

    @Test
    fun `shouldAudit`() {

        auditController.audit(date, date, null, "")
        whenever(auditConverter.requestToAuditDAO(null, date, date)) doReturn buildAuditDAO()

        verify(authRequestValidatorComponent, times(1)).validateApiKey("")
        verify(auditConverter, times(1)).requestToAuditDAO(null, date, date)
        assertEquals(OK, auditController.audit(date, date, null, "").statusCode)
    }

    @Test
    fun `shouldDelete`() {
        auditController.delete()
        verify(auditService, times(1)).deleteAll()
    }

    private fun buildAuditDAO() = AuditDAO(null, date, date)
}