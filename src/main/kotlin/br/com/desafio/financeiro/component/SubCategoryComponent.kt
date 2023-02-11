package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.exception.CategoryCanNotBeDeletedException
import br.com.desafio.financeiro.repository.customRepository.SubCategoriesRepositoryImpl
import br.com.desafio.financeiro.service.AccountEntryService
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class SubCategoryComponent(
        val subCategoriesRepositoryImpl: SubCategoriesRepositoryImpl,
        val accountEntryService: AccountEntryService
) {
    val logger: Logger = Logger.getLogger(javaClass.name)

    fun hasSubCategoryWithName(name: String): Boolean {
        logger.info("action=VerifyingIfHasNotSubCategoryWithName, name=$name")
        val category = subCategoriesRepositoryImpl.findByName(name)
        return category.isNotEmpty()
    }

    fun getIdsToDeleteSubCategoriesFromCategory(idCategory: Int): MutableList<Int> {
        val accountEntries = accountEntryService.getAllAccountEntry()
        logger.info("action=FindingAllSubCategoriesByIdCategory")
        val subCategories = subCategoriesRepositoryImpl.findAllByIdCategory(idCategory)
        val idsToDelete: MutableList<Int> = mutableListOf()

        accountEntries.forEach { accountEntry ->
            run {
                subCategories.forEach { subCategory ->
                    run {
                        if (accountEntry.idSubCategory == subCategory.idSubCategory) {
                            logger.info("action=AccountEntryHasSubCategoryLinked, name=${subCategory.name}")
                            throw CategoryCanNotBeDeletedException()
                        }
                    }
                }
            }
        }

        logger.info("action=BuildingListOfIdsToBeDeleted")
        subCategories.forEach { subCategory ->
            run {
                idsToDelete.add(subCategory.idSubCategory!!)
            }
        }

        return idsToDelete
    }
}
