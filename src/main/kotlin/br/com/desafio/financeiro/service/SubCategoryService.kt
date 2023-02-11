package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.exception.SubCategoryNotFoundException
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.model.SubCategoriesEntity
import br.com.desafio.financeiro.repository.CategoriesRepository
import br.com.desafio.financeiro.repository.SubCategoriesRepository
import org.springframework.stereotype.Service

@Service
class SubCategoryService(
        val subCategoriesRepository: SubCategoriesRepository//,
        //val categoriesService: CategoriesService
) {
    fun createSubCategory(subCategory: SubCategoriesEntity) {
        //categoriesService.getCategoryById(subCategory.idCategory!!)
//        subCategoriesRepository.getSubCategoryByName(category.nome) ?: throw RuntimeException("Ja existe uma sub categoria de mesmo nome")

        subCategoriesRepository.save(subCategory)
    }

    fun getSubCategoryById(id: Int): SubCategoriesEntity? {
        return subCategoriesRepository.findById(id).orElseThrow { SubCategoryNotFoundException() }
    }

    fun getAllSubCategories(): MutableIterable<SubCategoriesEntity> {
        return subCategoriesRepository.findAll()
    }

    fun deleteSubCategory(id: Int) {
        subCategoriesRepository.deleteById(id)
    }

    fun deleteSubCategoriesFromCategory(idCategory: Int) {
        // subCategoriesRepositoryImpl.deleteAllByCategoryId(idCategory)
    }

    fun updateSubCategory(subCategory: SubCategoriesEntity) {
        subCategoriesRepository.findById(subCategory.idSubCategory!!).orElseThrow { SubCategoryNotFoundException() }
        subCategoriesRepository.save(subCategory)
    }
}