package com.example.demo.service.db.entities

import java.math.BigDecimal
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class PortfolioPosition(
  @Id val id: Long,
  val portfolioId: String,
  val isin: String,
  val amount: BigDecimal
)