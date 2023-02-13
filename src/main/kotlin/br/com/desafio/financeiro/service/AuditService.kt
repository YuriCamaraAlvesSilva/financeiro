package br.com.desafio.financeiro.service

import br.com.desafio.financeiro.component.AuditComponent
import br.com.desafio.financeiro.component.CategoryComponent
import br.com.desafio.financeiro.converter.AuditConverter
import br.com.desafio.financeiro.model.AuditDAO
import br.com.desafio.financeiro.model.response.AuditResponse
import br.com.desafio.financeiro.repository.AccountEntryRepository
import br.com.desafio.financeiro.repository.CategoriesRepository
import br.com.desafio.financeiro.repository.SubCategoriesRepository
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AuditService(
        val accountEntryRepository: AccountEntryRepository,
        val categoryRepository: CategoriesRepository,
        val categoryComponent: CategoryComponent,
        val subCategoryRepository: SubCategoriesRepository,
        val auditComponent: AuditComponent,
        val auditConverter: AuditConverter
) {
    val logger: Logger = Logger.getLogger(javaClass.name)

    fun audit(auditDAO: AuditDAO): AuditResponse {
        val balance = auditComponent.audit(auditDAO)
        return if (auditComponent.hasIdCategory(auditDAO)) {
            val category = categoryComponent.getCategoryWithId(auditDAO.idCategory!!)
            logger.info("action=buildResponseToAudit")
            auditConverter.auditResponse(category = category, auditDTO = balance)
        } else {
            logger.info("action=buildResponseToAudit")
            auditConverter.auditResponse(category = null, auditDTO = balance)
        }
    }

    fun deleteAll() {
        logger.info("action=CleaningAllDatabases")
        accountEntryRepository.deleteAll()
        categoryRepository.deleteAll()
        subCategoryRepository.deleteAll()
    }
}