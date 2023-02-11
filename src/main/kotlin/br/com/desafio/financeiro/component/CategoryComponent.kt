package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.exception.CategoryCreateException
import br.com.desafio.financeiro.exception.CategoryNotFoundException
import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.repository.CategoriesRepository
import br.com.desafio.financeiro.repository.customRepository.CategoriesRepositoryImpl
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class CategoryComponent(
        val categoriesRepositoryImpl: CategoriesRepositoryImpl,
        val categoriesRepository: CategoriesRepository
) {
    val logger: Logger = Logger.getLogger(javaClass.name)
    fun hasCategoryWithName(name: String): Boolean {
        logger.info("action=VerifyingIfHasNotCategoryWithTheName, name=$name")
        val category = categoriesRepositoryImpl.findByName(name)
        return category.isNotEmpty()
    }

    fun getCategoryWithId(id: Int): CategoriesEntity {
        logger.info("action=VerifyingIfHasCategoryWithTheName")
        return categoriesRepository.findById(id).orElseThrow {
            logger.info("action=CategoryNotExists")
            CategoryNotFoundException()
        }
    }
}