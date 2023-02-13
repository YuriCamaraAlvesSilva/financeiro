package br.com.desafio.financeiro.controller

import br.com.desafio.financeiro.component.AuthRequestValidatorComponent
import br.com.desafio.financeiro.model.SubCategoriesEntity
import br.com.desafio.financeiro.service.SubCategoryService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class SubCategoriesController(
        val authRequestValidatorComponent: AuthRequestValidatorComponent,
        val subCategoryService: SubCategoryService
) {
    @GetMapping("/sub-categories")
    fun getSubCategories(@RequestHeader("api-key") apiKey: String): ResponseEntity<MutableIterable<SubCategoriesEntity>> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val subCategories = subCategoryService.getAllSubCategories()
        return ResponseEntity.ok().body(subCategories)
    }

    @GetMapping("/sub-categories/{idSubCategory}")
    fun getSubCategory(
            @RequestHeader("api-key") apiKey: String,
            @PathVariable("idSubCategory") id: Int
    ): ResponseEntity<SubCategoriesEntity> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val subCategory = subCategoryService.getSubCategoryById(id)
        return ResponseEntity.ok().body(subCategory)
    }

    @PostMapping("/sub-categories")
    fun createSubCategory(
            @RequestHeader("api-key") apiKey: String,
            @RequestBody subCategoriesEntity: SubCategoriesEntity
    ): ResponseEntity<SubCategoriesEntity> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val body = subCategoryService.createSubCategory(subCategoriesEntity)
        return ResponseEntity.status(CREATED).body(body)
    }

    @PutMapping("/sub-categories")
    fun updateSubCategory(
            @RequestHeader("api-key") apiKey: String,
            @RequestBody subCategoriesEntity: SubCategoriesEntity
    ): ResponseEntity<SubCategoriesEntity> {
        authRequestValidatorComponent.validateApiKey(apiKey)
        val body = subCategoryService.updateSubCategory(subCategoriesEntity)
        return ResponseEntity.ok(body)
    }

}