package br.com.desafio.financeiro.repository

import br.com.desafio.financeiro.model.SubCategoriesEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubCategoriesRepository : CrudRepository<SubCategoriesEntity, Int>