package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.component.CategoryComponent
import br.com.desafio.financeiro.component.SubCategoryComponent
import br.com.desafio.financeiro.exception.SubCategoryCreateException
import br.com.desafio.financeiro.exception.SubCategoryNotFoundException
import br.com.desafio.financeiro.model.SubCategoriesEntity
import br.com.desafio.financeiro.repository.SubCategoriesRepository
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class SubCategoryService(
        val subCategoriesRepository: SubCategoriesRepository,
        val subCategoryComponent: SubCategoryComponent,
        val categoryComponent: CategoryComponent
) {
    val logger: Logger = Logger.getLogger(javaClass.name)

    fun createSubCategory(subCategory: SubCategoriesEntity): SubCategoriesEntity {
        categoryComponent.getCategoryWithId(subCategory.idCategory!!)
        if (subCategoryComponent.hasSubCategoryWithName(subCategory.name)) {
            throw SubCategoryCreateException()
        }
        logger.info("action=SavingNewSubCategory, name=${subCategory.name}")
        return subCategoriesRepository.save(subCategory)
    }

    fun getSubCategoryById(id: Int): SubCategoriesEntity? {
        return subCategoriesRepository.findById(id).orElseThrow { SubCategoryNotFoundException() }
    }

    fun getAllSubCategories(): MutableIterable<SubCategoriesEntity> {
        return subCategoriesRepository.findAll()
    }

    fun deleteAllSubCategoriesByCategoryId(id: Int) {
        val idsToDelete = subCategoryComponent.getIdsToDeleteSubCategoriesFromCategory(id)
        return subCategoriesRepository.deleteAllById(idsToDelete)
    }


    fun updateSubCategory(subCategory: SubCategoriesEntity): SubCategoriesEntity {
        subCategoriesRepository.findById(subCategory.idSubCategory!!).orElseThrow { SubCategoryNotFoundException() }
        return subCategoriesRepository.save(subCategory)
    }
}