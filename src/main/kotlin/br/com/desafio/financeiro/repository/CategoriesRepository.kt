package br.com.desafio.financeiro.repository

import br.com.desafio.financeiro.model.CategoriesEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriesRepository : CrudRepository<CategoriesEntity, Int>