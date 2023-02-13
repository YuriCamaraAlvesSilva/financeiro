package br.com.desafio.financeiro.controller

import br.com.desafio.financeiro.component.AuthRequestValidatorComponent
import br.com.desafio.financeiro.converter.AuditConverter
import br.com.desafio.financeiro.model.response.AuditResponse
import br.com.desafio.financeiro.service.AuditService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.Date
import java.time.LocalDate

@RestController
@RequestMapping("/v1")
class AuditController(
        val authRequestValidatorComponent: AuthRequestValidatorComponent,
        val auditService: AuditService,
        val auditConverter: AuditConverter
) {
    @GetMapping("/audit")
    fun audit(
            @RequestParam startDate: Date? = Date.valueOf(LocalDate.MIN),
            @RequestParam endDate: Date? = Date.valueOf(LocalDate.now()),
            @RequestParam idCategory: Int? = null,
            @RequestHeader("api-key") apiKey: String
    ): ResponseEntity<AuditResponse> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val body = auditService.audit(auditConverter.requestToAuditDAO(idCategory, startDate, endDate))
        return ResponseEntity.ok().body(body)
    }

    @DeleteMapping("/audit")
    fun delete() {
        auditService.deleteAll()
        ResponseEntity.ok()
    }
}