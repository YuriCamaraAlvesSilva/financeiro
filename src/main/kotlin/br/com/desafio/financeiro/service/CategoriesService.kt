package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.exception.CategoryNotFoundException
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.repository.CategoriesRepository
import org.springframework.stereotype.Service

@Service
class CategoriesService(
        val categoriesRepository: CategoriesRepository
) {
    fun createCategory(category: CategoriesEntity) {
//        categoriesRepository.getCategoryByName(category.nome).orElseThrow {RuntimeException("Ja existe uma categoria de mesmo nome")}
        categoriesRepository.save(category)
    }

    fun getCategoryById(id: Int): CategoriesEntity {
        return categoriesRepository.findById(id).orElseThrow { CategoryNotFoundException() }
    }

    fun deleteCategory(id: Int) {
        return categoriesRepository.deleteById(id)
    }

    fun updateCategory(category: CategoriesEntity) {
        category.idCategory?.let { categoriesRepository.findById(it).orElseThrow { CategoryNotFoundException() } }
        categoriesRepository.save(category)
    }
}