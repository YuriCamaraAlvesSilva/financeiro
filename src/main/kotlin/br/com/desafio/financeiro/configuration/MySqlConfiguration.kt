package br.com.desafio.financeiro.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

@Component
class MySqlConfiguration(
        @Value("\${database.url}") val databaseUrl: String = ""
) {
    fun getConnection(): Connection {
        val properties = Properties()

        with(properties) {
            put("user", "root")
            put("password", "")
        }

        return DriverManager.getConnection(databaseUrl, properties)
    }
}