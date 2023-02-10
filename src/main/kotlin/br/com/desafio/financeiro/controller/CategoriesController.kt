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
        val categories = categoriesService.getAllCategories()
        return ResponseEntity.ok().body(categories)
    }

    @GetMapping("/categories/{idCategory}")
    fun getCategory(
            @RequestHeader("api-key") apiKey: String,
            @PathVariable("idCategory") idCategory: Int
    ): ResponseEntity<CategoriesEntity> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val category = categoriesService.getCategoryById(idCategory)
        return ResponseEntity.ok().body(category)
    }

    @PostMapping("/categories")
    fun createCategory(
            @RequestHeader("api-key") apiKey: String,
            @RequestBody categoryEntity: CategoriesEntity
    ): ResponseEntity<() -> Unit> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        categoriesService.createCategory(categoryEntity)
        return ResponseEntity.status(HttpStatus.CREATED).body({})
    }

    @PutMapping("/categories")
    fun updateCategory(
            @RequestHeader("api-key") apiKey: String,
            @RequestBody categoryEntity: CategoriesEntity
    ): ResponseEntity.BodyBuilder {
        authRequestValidatorComponent.validateApiKey(apiKey)
        categoriesService.updateCategory(categoryEntity)
        return ResponseEntity.ok()
    }

    @DeleteMapping("/categories/{idCategory}")
    fun deleteCategory(
            @RequestHeader("api-key") apiKey: String,
            @PathVariable("idCategory") idCategory: Int
    ): ResponseEntity.BodyBuilder {
        authRequestValidatorComponent.validateApiKey(apiKey)
        categoriesService.deleteCategory(idCategory)
        return ResponseEntity.ok()
    }
}