package com.example.demo.service.db.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
data class PortfolioPosition(
  @Id val id: Long,
  val portfolioId: String,
  val isin: String,
  val amount: BigDecimal
)