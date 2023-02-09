package br.com.desafio.financeiro.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SubCategoriesController {
    @GetMapping("/subcategories")
    fun getSubCategories() {
        print("ok")
        ResponseEntity.ok()
    }

    @GetMapping("/subcategories/{idCategory}")
    fun getSubCategory(@RequestParam idCategory: Int) {
        print("ok")
        ResponseEntity.ok()
    }

    @PostMapping("/subcategories")
    fun createSubCategory() {
        print("ok")
        ResponseEntity.ok()
    }

    @PutMapping("/subcategories")
    fun updateSubCategory() {
        print("ok")
        ResponseEntity.ok()
    }

    @DeleteMapping("/subcategories")
    fun deleteSubCategory() {
        print("ok")
        ResponseEntity.ok()
    }
}