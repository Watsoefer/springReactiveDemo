package com.example.demo.dto

import java.math.BigDecimal

data class Portfolio(
  val id: String,
  val name: String,
  val instruments: List<Instrument>
)

data class Instrument(
  val isin: Isin,
  val name: String,
  val bid: BigDecimal? = null,
  val ask: BigDecimal? = null,
  val current: BigDecimal,
  val currency: Currency? = null
)

@JvmInline
value class Currency(val code: String) {
  init {
    require(code.length == 3) { "Currency code $code is not valid" }
  }
}

@JvmInline
value class Isin(val isin: String) {
  init {
    require(isin.length == 12) { "Invalid ISIN: $isin" }
  }
}