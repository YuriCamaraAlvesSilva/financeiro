package br.com.desafio.financeiro.repository.customRepository

import br.com.desafio.financeiro.model.CategoriesEntity
import org.springframework.beans.factory.annotation.Value
import java.sql.Connection
import java.sql.DriverManager
import java.util.*


class CategoriesRepositoryImpl(
        @Value("\${database.url}") val databaseUrl: String = ""
) {
    fun findBy(field: String, source: String): MutableList<CategoriesEntity> {
        val properties = Properties()

        with(properties) {
            put("user", "root")
            put("password", "")
        }

        val result: MutableList<CategoriesEntity> = DriverManager
                .getConnection(databaseUrl, properties)
                .use { connection ->
                    queryRows(connection, field, source)
                }
        return result
    }

    private fun queryRows(connection: Connection, field: String, source: String): MutableList<CategoriesEntity> {
        val result: MutableList<CategoriesEntity> = mutableListOf()
        val query = "SELECT * FROM categories_entity WHERE $field LIKE \'$source\'"
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