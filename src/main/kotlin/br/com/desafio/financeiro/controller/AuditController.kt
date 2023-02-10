package br.com.desafio.financeiro.controller

import br.com.desafio.financeiro.component.AuthRequestValidatorComponent
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/v1")
class AuditController(val authRequestValidatorComponent: AuthRequestValidatorComponent) {
    @GetMapping("/audit")
    fun audit(
            @RequestParam startDate: LocalDate,
            @RequestParam endDate: LocalDate,
            @RequestParam idCategory: Int?,
            @RequestHeader("api-key") apiKey: String
    ){
        authRequestValidatorComponent.validateApiKey(apiKey)
        ResponseEntity.ok()
    }
}