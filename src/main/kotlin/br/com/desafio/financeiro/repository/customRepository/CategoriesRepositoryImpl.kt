package br.com.desafio.financeiro.repository.customRepository

import br.com.desafio.financeiro.configuration.MySqlConfiguration
import br.com.desafio.financeiro.model.CategoriesEntity


open class CategoriesRepositoryImpl(
        mySqlConfiguration: MySqlConfiguration
) {
    private val connection = mySqlConfiguration.getConnection()

    fun findByName(source: String): MutableList<CategoriesEntity> {
        val result: MutableList<CategoriesEntity> = mutableListOf()
        val query = "SELECT * FROM categories_entity WHERE name LIKE \'$source\'"
        val rs = connection.createStatement().executeQuery(query)
        while (rs.next()) {
            result.add(CategoriesEntity(
                    idCategory = rs.getInt("id_category"),
                    name = rs.getString("name")
            ))
        }
        return result
    }
}