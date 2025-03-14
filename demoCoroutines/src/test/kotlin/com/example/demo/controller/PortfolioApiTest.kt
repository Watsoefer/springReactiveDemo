package com.example.demo.controller

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.demo.dto.Portfolio
import com.example.demo.service.PortfolioService
import com.example.demo.service.db.entities.PortfolioPosition
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PortfolioApiTest {

  @Autowired
  lateinit var client: WebTestClient

  @MockitoBean
  lateinit var portfolioService: PortfolioService

  @Test
  fun `test call to get portfolio`() = runTest {
    // GIVEN
    val portfolioId = "12345"
    whenever(portfolioService.getPortfolioPositions(any())).thenReturn(
      flow { emit(PortfolioPosition(1, "1", "DE0007100000", BigDecimal.ONE)) }
    )
      // WHEN
      val result = client.get().uri("/portfolios/{portfolioId}", portfolioId)
        .exchange()
        .expectStatus().isOk
        .expectBody(Portfolio::class.java)
        .returnResult().responseBody
    // THEN
    assertThat(result?.id).isEqualTo(portfolioId)
  }
}