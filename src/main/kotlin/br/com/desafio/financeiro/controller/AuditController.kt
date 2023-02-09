package br.com.desafio.financeiro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class AuditController {
    @GetMapping("/audit")
    fun audit(
            @RequestAttribute startDate: LocalDate,
            @RequestAttribute endDate: LocalDate,
            @RequestAttribute idCategory: Int?
    ){
        print("ok")
        ResponseEntity.ok()
    }
}