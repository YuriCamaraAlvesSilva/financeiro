package br.com.desafio.financeiro.repository.customRepository

import br.com.desafio.financeiro.repository.AccountEntryRepository
import org.springframework.jdbc.core.JdbcTemplate

abstract class AccountEntryRepositoryImpl(
        private val mysqlTemplate: JdbcTemplate
): AccountEntryRepository {
    fun findAccountEntryByName(name: String) {
        val query = "SELECT * FROM account_entry_entity WHERE nome LIKE $name"
        return mysqlTemplate.execute(query)
    }
}