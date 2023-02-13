package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.component.CategoryComponent
import br.com.desafio.financeiro.exception.CategoryAlreadyExistsException
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.repository.CategoriesRepository
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class CategoriesService(
        val categoriesRepository: CategoriesRepository,
        val categoryComponent: CategoryComponent,
        val subCategoryService: SubCategoryService

) {
    val logger: Logger = Logger.getLogger(javaClass.name)
    fun createCategory(category: CategoriesEntity): CategoriesEntity {
        if (categoryComponent.hasCategoryWithName(category.name)) {
            logger.info("CategoryAlreadyExists, name=${category.name}")
            throw CategoryAlreadyExistsException()
        }
        logger.info("action=SavingNewCategory, name=${category.name}")
        return categoriesRepository.save(category)
    }

    fun getCategoryById(id: Int): CategoriesEntity {
        logger.info("action=SearchingForCategoryById")
        return categoryComponent.getCategoryWithId(id)
    }

    fun getAllCategories(): MutableIterable<CategoriesEntity> {
        logger.info("action=GettingAllCategories")
        return categoriesRepository.findAll()
    }

    fun deleteCategory(id: Int) {
        logger.info("action=RemovingSubCategoriesByIdCategory")
        subCategoryService.deleteAllSubCategoriesByCategoryId(id)
        categoriesRepository.deleteById(id)
    }

    fun updateCategory(category: CategoriesEntity): CategoriesEntity {
        if (categoryComponent.hasCategoryWithName(category.name)) {
            throw CategoryAlreadyExistsException()
        }
        getCategoryById(category.idCategory!!)
        logger.info("action=UpdatingCategory, name=${category.name}")
        return categoriesRepository.save(category)
    }
}