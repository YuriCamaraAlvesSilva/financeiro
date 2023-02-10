package br.com.desafio.financeiro.repository.customRepository

import br.com.desafio.financeiro.model.CategoriesEntity
import br.com.desafio.financeiro.repository.CategoriesRepository
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*


abstract class CategoriesRepositoryImpl(
         val jdbcTemplate: JdbcTemplate
) : CategoriesRepository {
    fun findCategoryByName(name: String): Any {
        val query = "SELECT * FROM category_entity WHERE nome LIKE $name"
        return jdbcTemplate.execute(query)
    }
}