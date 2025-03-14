package com.example.demo.service.db.repository

import com.example.demo.service.db.entities.PortfolioPosition
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface PortfolioRepository: ReactiveCrudRepository<PortfolioPosition, Long> {
  fun findAllByPortfolioId(portfolioId: String): Flux<PortfolioPosition>
}