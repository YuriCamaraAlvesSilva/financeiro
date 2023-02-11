package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.exception.CategoryCreateException
import br.com.desafio.financeiro.repository.customRepository.CategoriesRepositoryImpl
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class CategoryComponent(
        val categoriesRepositoryImpl: CategoriesRepositoryImpl
) {
    val logger: Logger = Logger.getLogger(javaClass.name)
    fun hasCategory(name: String) {
        logger.info("action=VerifyingIfHasCategoryWithTheName, name=$name")
        val category = categoriesRepositoryImpl.findBy("name", name)
        if (category.isNotEmpty()) {
            logger.info("action=CategoryAlreadyExists, name=$name, idCategory=${category.first().idCategory}")
            throw CategoryCreateException()
        }
    }
}