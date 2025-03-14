package com.example.demo.service

import com.example.demo.service.db.repository.PortfolioRepository
import org.springframework.stereotype.Component

@Component
class PortfolioService(private val portfolioRepository: PortfolioRepository) {

  fun getPortfolioPositions(portfolioId: String) = portfolioRepository.findAllByPortfolioId(portfolioId)

}