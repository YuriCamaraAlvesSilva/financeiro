package br.com.desafio.financeiro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AccountEntryController {
    @GetMapping("/account-entry")
    fun getAccountEntry() {
        print("ok")
        ResponseEntity.ok()
    }

    @GetMapping("/account-entry/{idCategory}")
    fun getAccountEntry(@RequestParam idCategory: Int) {
        print("ok")
        ResponseEntity.ok()
    }

    @PostMapping("/account-entry")
    fun createAccountEntry() {
        print("ok")
        ResponseEntity.ok()
    }

    @PutMapping("/account-entry")
    fun updateAccountEntry() {
        print("ok")
        ResponseEntity.ok()
    }

    @DeleteMapping("/account-entry")
    fun deleteAccountEntry() {
        print("ok")
        ResponseEntity.ok()
    }
}