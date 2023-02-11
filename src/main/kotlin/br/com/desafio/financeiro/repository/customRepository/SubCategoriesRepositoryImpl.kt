package br.com.desafio.financeiro.repository.customRepository

import br.com.desafio.financeiro.model.SubCategoriesEntity
import org.springframework.beans.factory.annotation.Value
import java.sql.Connection
import java.sql.DriverManager
import java.util.*


class SubCategoriesRepositoryImpl(
        @Value("\${database.url}") val databaseUrl: String = ""
) {
    fun findBy(field: String, source: String): MutableList<SubCategoriesEntity> {
        val properties = Properties()

        with(properties) {
            put("user", "root")
            put("password", "")
        }

        val result: MutableList<SubCategoriesEntity> = DriverManager
                .getConnection(databaseUrl, properties)
                .use { connection ->
                    queryRows(connection, field, source)
                }
        return result
    }

    private fun queryRows(connection: Connection, field: String, source: String): MutableList<SubCategoriesEntity> {
        val result: MutableList<SubCategoriesEntity> = mutableListOf()
        val query = "SELECT * FROM sub_categories_entity WHERE $field LIKE $source"
        val rs = connection.createStatement().executeQuery(query)
        while (rs.next()) {
            result.add(SubCategoriesEntity(
                    idSubCategory = rs.getInt("id_sub_category"),
                    name = rs.getString("name"),
                    idCategory = rs.getInt("id_category")
            ))
        }
        return result
    }
}