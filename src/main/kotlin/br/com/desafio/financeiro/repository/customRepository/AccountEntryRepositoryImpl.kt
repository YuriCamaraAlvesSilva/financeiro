package br.com.desafio.financeiro.repository.customRepository

import br.com.desafio.financeiro.model.AccountEntryEntity
import org.springframework.beans.factory.annotation.Value
import java.sql.Connection
import java.sql.DriverManager
import java.util.Properties


class AccountEntryRepositoryImpl(
        @Value("\${database.url}") val databaseUrl: String = ""
) {
    fun find(field: String, source: String): MutableList<AccountEntryEntity> {
        val properties = Properties()

        with(properties) {
            put("user", "root")
            put("password", "")
        }

        val result: MutableList<AccountEntryEntity> = DriverManager
                .getConnection(databaseUrl, properties)
                .use { connection ->
                    queryRows(connection, field, source)
                }
        return result
    }

    private fun queryRows(connection: Connection, field: String, source: String): MutableList<AccountEntryEntity> {
        val result: MutableList<AccountEntryEntity> = mutableListOf()
        val query = "SELECT * FROM account_entry_entity WHERE $field LIKE $source"
        val rs = connection.createStatement().executeQuery(query)
        while (rs.next()) {
            result.add(AccountEntryEntity(
                    idAccountEntry = rs.getInt("id_account_entry"),
                    value = rs.getDouble("value"),
                    date = rs.getDate("date"),
                    idSubCategory = rs.getInt("id_sub_category"),
                    comment = rs.getString("comment")
            ))
        }
        return result
    }
}