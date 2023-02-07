package br.com.desafio.financeiro.repository

import br.com.desafio.financeiro.model.AccountEntryEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountEntryRepository : CrudRepository<AccountEntryEntity, Int>