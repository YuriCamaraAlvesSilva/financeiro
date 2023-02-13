package br.com.desafio.financeiro.repository.customRepository

import br.com.desafio.financeiro.configuration.MySqlConfiguration
import br.com.desafio.financeiro.model.AccountEntryEntity
import java.sql.Date


open class AccountEntryRepositoryImpl(
        mySqlConfiguration: MySqlConfiguration
) {
    private val connection = mySqlConfiguration.getConnection()

    fun findByAccountEntriesByDate(startDate: Date, endDate: Date): MutableList<AccountEntryEntity> {
        val result: MutableList<AccountEntryEntity> = mutableListOf()
        val query = "SELECT * FROM account_entry_entity WHERE date >= \'$startDate\' AND date <= \'$endDate\'"
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