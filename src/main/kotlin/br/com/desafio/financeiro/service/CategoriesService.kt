package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.exception.CategoryNotFoundException
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.repository.CategoriesRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoriesService(
        val categoriesRepository: CategoriesRepository
) {
    fun createCategory(category: CategoriesEntity) {
//        val test = categoriesRepositoryImpl.findCategoryByName(category.nome)
//        print(teste)
        categoriesRepository.save(category)
    }

    fun getCategoryById(id: Int): CategoriesEntity {
        return categoriesRepository.findById(id).orElseThrow { CategoryNotFoundException() }
    }

    fun getAllCategories(): MutableIterable<CategoriesEntity> {
        return categoriesRepository.findAll()
    }

    fun deleteCategory(id: Int) {
        return categoriesRepository.deleteById(id)
    }

    fun updateCategory(category: CategoriesEntity) {
        category.idCategory?.let { categoriesRepository.findById(it).orElseThrow { CategoryNotFoundException() } }
        categoriesRepository.save(category)
    }
}