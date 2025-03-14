package com.example.demo.service

import com.example.demo.dto.Currency
import com.example.demo.dto.Instrument
import com.example.demo.dto.Isin
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.math.BigDecimal


@Component
class PriceService(private val clientBuilder: WebClient.Builder) {
  private val client: WebClient = clientBuilder.baseUrl("http://localhost:8181").build()

  suspend fun getCurrentPrices(instruments: List<Isin>): List<Instrument> {
    val prices = client.get().uri("/prices?isins={isins}", instruments.joinToString(","))
      .exchangeToMono { response ->
        if (response.statusCode() == HttpStatus.OK) {
          response.bodyToMono<PricesDTO>()
        } else {
          throw Exception("Error while reading prices: ${response.statusCode()}")
        }
      }
    return prices.map {
      it.prices.map { it.toInstrument() }
    }.awaitSingle()
  }
}

data class PricesDTO(val prices: List<PriceDTO>)
data class PriceDTO(
  val isin: Isin,
  val name: String,
  val currency: Currency,
  val ask: BigDecimal,
  val bid: BigDecimal,
  val current: BigDecimal
)

private fun PriceDTO.toInstrument() = Instrument(
  isin = isin,
  name = name,
  currency = currency,
  ask = ask,
  bid = bid,
  current = current
)