package br.com.desafio.financeiro.controller

import br.com.desafio.financeiro.component.AuthRequestValidatorComponent
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.service.CategoriesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class CategoriesController(
        val authRequestValidatorComponent: AuthRequestValidatorComponent,
        val categoriesService: CategoriesService
) {
    @GetMapping("/categories")
    fun getCategories(@RequestHeader("api-key") apiKey: String): ResponseEntity<MutableIterable<CategoriesEntity>> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val body = categoriesService.getAllCategories()
        return ResponseEntity.ok().body(body)
    }

    @GetMapping("/categories/{idCategory}")
    fun getCategory(
            @RequestHeader("api-key") apiKey: String,
            @PathVariable("idCategory") idCategory: Int
    ): ResponseEntity<CategoriesEntity> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val body = categoriesService.getCategoryById(idCategory)
        return ResponseEntity.ok().body(body)
    }

    @PostMapping("/categories")
    fun createCategory(
            @RequestHeader("api-key") apiKey: String,
            @RequestBody categoryEntity: CategoriesEntity
    ): ResponseEntity<CategoriesEntity> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val body = categoriesService.createCategory(categoryEntity)
        return ResponseEntity.status(HttpStatus.CREATED).body(body)
    }

    @PutMapping("/categories")
    fun updateCategory(
            @RequestHeader("api-key") apiKey: String,
            @RequestBody categoryEntity: CategoriesEntity
    ): ResponseEntity<CategoriesEntity> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val body = categoriesService.updateCategory(categoryEntity)
        return ResponseEntity.ok().body(body)
    }

    @DeleteMapping("/categories/{idCategory}")
    fun deleteCategory(
            @RequestHeader("api-key") apiKey: String,
            @PathVariable("idCategory") idCategory: Int
    ) {
        authRequestValidatorComponent.validateApiKey(apiKey)
        categoriesService.deleteCategory(idCategory)
        ResponseEntity.ok()
    }
}