package com.example.demo.controller

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.demo.accessing_data_mysql.TestcontainersConfiguration
import com.example.demo.dto.Instrument
import com.example.demo.dto.Isin
import com.example.demo.dto.Portfolio
import com.example.demo.service.PortfolioService
import com.example.demo.service.PriceService
import com.example.demo.service.db.entities.PortfolioPosition
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Import
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration::class)
class PortfolioApiTest {

  @Autowired
  lateinit var client: TestRestTemplate

  @MockitoBean
  lateinit var portfolioService: PortfolioService

  @MockitoBean
  lateinit var priceService: PriceService

  @Test
  fun `test call to get portoflio`() {
    // GIVEN
    whenever(portfolioService.getPortfolioPositions(any())).thenReturn(
      listOf(PortfolioPosition(1, "1", "DE0007100000", BigDecimal.ONE))
    )
    whenever(priceService.getCurrentPrices(any())).thenReturn(
      listOf(Instrument(isin = Isin("DE0007100000"), name="Mercedes", ask = BigDecimal.ONE, bid = BigDecimal.ONE, current = BigDecimal.ONE))
    )
    val portfolioId = "12345"
    // WHEN
    val result = client.getForEntity("/portfolios/{portfolioId}", Portfolio::class.java, portfolioId).body!!
    // THEN
    assertThat(result.id).isEqualTo(portfolioId)
  }
}