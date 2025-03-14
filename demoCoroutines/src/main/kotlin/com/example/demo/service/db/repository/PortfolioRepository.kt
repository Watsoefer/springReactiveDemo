package com.example.demo.service.db.repository

import com.example.demo.service.db.entities.PortfolioPosition
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface PortfolioRepository: CoroutineCrudRepository<PortfolioPosition, Long> {
  suspend fun findAllByPortfolioId(portfolioId: String): Flow<PortfolioPosition>
}