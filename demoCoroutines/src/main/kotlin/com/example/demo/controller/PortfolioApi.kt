package com.example.demo.controller

import com.example.demo.dto.Isin
import com.example.demo.dto.Portfolio
import com.example.demo.service.PortfolioService
import com.example.demo.service.PriceService
import io.micrometer.core.annotation.Timed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PortfolioApi(
  private val priceService: PriceService,
  private val portfolioService: PortfolioService
) {

  @GetMapping("/portfolios/{portfolioId}")
  @Timed
  suspend fun getPortfolio(@PathVariable portfolioId: String): ResponseEntity<Portfolio> {
    val isinList = portfolioService.getPortfolioPositions(portfolioId).map { Isin(it.isin) }.toList()
    val instruments = priceService.getCurrentPrices(isinList)
    return ResponseEntity.ok(
      Portfolio(
        id = portfolioId,
        name = "Direkt-Depot",
        instruments = instruments
      )
    )
  }
}