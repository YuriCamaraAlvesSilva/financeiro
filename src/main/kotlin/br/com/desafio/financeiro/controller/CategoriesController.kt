package br.com.desafio.financeiro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CategoriesController {
    @GetMapping("/account-entry")
    fun getCategories() {
        print("ok")
        ResponseEntity.ok()
    }

    @GetMapping("/account-entry/{idCategory}")
    fun getCategory(@RequestParam idCategory: Int) {
        print("ok")
        ResponseEntity.ok()
    }

    @PostMapping("/account-entry")
    fun createCategory() {
        print("ok")
        ResponseEntity.ok()
    }

    @PutMapping("/account-entry")
    fun updateCategory() {
        print("ok")
        ResponseEntity.ok()
    }

    @DeleteMapping("/account-entry")
    fun deleteCategory() {
        print("ok")
        ResponseEntity.ok()
    }
}