package com.example.demo.service

import com.example.demo.dto.Currency
import com.example.demo.dto.Instrument
import com.example.demo.dto.Isin
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.math.BigDecimal


@Component
class PriceService(clientBuilder: RestClient.Builder, private val objectMapper: ObjectMapper) {
  private val client: RestClient = clientBuilder.baseUrl("http://localhost:8181").build()

  fun getCurrentPrices(instruments: List<Isin>): List<Instrument> {
    val prices = client.get().uri("/prices?isins={isins}", instruments.joinToString(","))
      .exchange { request, response ->
        if (response.statusCode == HttpStatus.OK) {
          objectMapper.readValue(response.body, PricesDTO::class.java)
        } else {
          throw Exception("Error while reading prices: ${response.statusCode}")
        }
      }
    return prices?.prices?.map { price -> price.toInstrument() } ?: emptyList()
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