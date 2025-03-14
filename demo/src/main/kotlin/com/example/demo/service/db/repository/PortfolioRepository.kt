package com.example.demo.service.db.repository

import com.example.demo.service.db.entities.PortfolioPosition
import org.springframework.data.repository.CrudRepository

interface PortfolioRepository:CrudRepository<PortfolioPosition, Long> {
  fun findAllByPortfolioId(portfolioId: String): List<PortfolioPosition>
}