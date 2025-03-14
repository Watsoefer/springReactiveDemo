package com.example.demo.controller

import com.example.demo.dto.Isin
import com.example.demo.dto.Portfolio
import com.example.demo.service.PortfolioService
import com.example.demo.service.PriceService
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
  fun getPortfolio(@PathVariable portfolioId: String): ResponseEntity<Portfolio> {
    val isinList = portfolioService.getPortfolioPositions(portfolioId).map { Isin(it.isin) }
    val instruments = priceService.getCurrentPrices(isinList)

    return ResponseEntity.ok().body(
      Portfolio(
        id = portfolioId,
        name = "Direkt-Depot",
        instruments = instruments
      )
    )
  }
}