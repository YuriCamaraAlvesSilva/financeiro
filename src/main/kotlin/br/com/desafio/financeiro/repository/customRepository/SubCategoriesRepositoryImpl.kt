package br.com.desafio.financeiro.repository.customRepository

import br.com.desafio.financeiro.configuration.MySqlConfiguration
import br.com.desafio.financeiro.model.SubCategoriesEntity


class SubCategoriesRepositoryImpl(
        mySqlConfiguration: MySqlConfiguration
) {
    private val connection = mySqlConfiguration.getConnection()

    fun findByName(source: String): MutableList<SubCategoriesEntity> {
        val result: MutableList<SubCategoriesEntity> = mutableListOf()
        val query = "SELECT * FROM sub_categories_entity WHERE name LIKE \'$source\'"
        val rs = connection.createStatement().executeQuery(query)
        while (rs.next()) {
            result.add(SubCategoriesEntity(
                    idCategory = rs.getInt("id_category"),
                    name = rs.getString("name"),
                    idSubCategory = rs.getInt("id_sub_category")
            ))
        }
        return result
    }

    fun findAllByIdCategory(source: Int): MutableList<SubCategoriesEntity> {
        val result: MutableList<SubCategoriesEntity> = mutableListOf()
        val query = "SELECT * FROM sub_categories_entity WHERE id_category LIKE $source"
        val rs = connection.createStatement().executeQuery(query)
        while (rs.next()) {
            result.add(SubCategoriesEntity(
                    idCategory = rs.getInt("id_category"),
                    name = rs.getString("name"),
                    idSubCategory = rs.getInt("id_sub_category")
            ))
        }
        return result
    }
}