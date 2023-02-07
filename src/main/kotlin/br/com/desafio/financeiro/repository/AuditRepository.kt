package br.com.desafio.financeiro.repository

import br.com.desafio.financeiro.model.AuditEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuditRepository : CrudRepository<AuditEntity, Int>