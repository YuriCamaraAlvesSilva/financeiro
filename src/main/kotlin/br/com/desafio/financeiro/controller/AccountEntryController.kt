package br.com.desafio.financeiro.controller

import br.com.desafio.financeiro.component.AuthRequestValidatorComponent
import br.com.desafio.financeiro.model.AccountEntryEntity
import br.com.desafio.financeiro.service.AccountEntryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/v1")
class AccountEntryController(
        val authRequestValidatorComponent: AuthRequestValidatorComponent,
        val accountEntryService: AccountEntryService
) {
    @GetMapping("/account-entry")
    fun getAccountEntries(@RequestHeader("api-key") apiKey: String): ResponseEntity<MutableIterable<AccountEntryEntity>> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val accountEntries = accountEntryService.getAllAccountEntry()
        return ResponseEntity.ok().body(accountEntries)
    }

    @GetMapping("/account-entry/{idAccountEntry}")
    fun getAccountEntry(
            @RequestHeader("api-key") apiKey: String,
            @PathVariable("idAccountEntry") idAccountEntry: Int
    ): ResponseEntity<AccountEntryEntity> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val accountEntry = accountEntryService.getAccountEntryById(idAccountEntry)
        return ResponseEntity.ok().body(accountEntry)
    }

    @PostMapping("/account-entry")
    fun createAccountEntry(
            @RequestHeader("api-key") apiKey: String,
            @RequestBody accountEntryEntity: AccountEntryEntity
    ): ResponseEntity.BodyBuilder {
        authRequestValidatorComponent.validateApiKey(apiKey)
        accountEntryService.createAccountEntry(accountEntryEntity)
        return ResponseEntity.status(HttpStatus.CREATED)
    }

    @PutMapping("/account-entry")
    fun updateAccountEntry(
            @RequestHeader("api-key") apiKey: String,
            @RequestBody accountEntryEntity: AccountEntryEntity
    ): ResponseEntity.BodyBuilder {
        authRequestValidatorComponent.validateApiKey(apiKey)
        accountEntryService.updateAccountEntry(accountEntryEntity)
        return ResponseEntity.ok()
    }

    @DeleteMapping("/account-entry/{idAccountEntry}")
    fun deleteAccountEntry(
            @RequestHeader("api-key") apiKey: String,
            @PathVariable("idAccountEntry") idAccountEntry: Int
    ): ResponseEntity.BodyBuilder {
        authRequestValidatorComponent.validateApiKey(apiKey)
        accountEntryService.deleteAccountEntry(idAccountEntry)
        return ResponseEntity.ok()
    }
}