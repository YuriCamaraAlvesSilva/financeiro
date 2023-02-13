package br.com.desafio.financeiro.component

import br.com.desafio.financeiro.repository.SubCategoriesRepository
import org.springframework.stereotype.Component

@Component
class AccountEntryComponent(
        val subCategoriesRepository: SubCategoriesRepository
) {
    fun hasSubCategoryWithId(id: Int): Boolean {
        return subCategoriesRepository.existsById(id)
    }
}
