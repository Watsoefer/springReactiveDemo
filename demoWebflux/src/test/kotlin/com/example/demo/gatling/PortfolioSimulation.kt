package com.example.demo.gatling

import io.gatling.javaapi.core.CoreDsl.constantUsersPerSec
import io.gatling.javaapi.core.CoreDsl.incrementUsersPerSec
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.ScenarioBuilder
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpProtocolBuilder

class PortfolioSimulation : Simulation() {
  var httpProtocol: HttpProtocolBuilder? =
    http.baseUrl("http://localhost:8080")
      .acceptHeader("application/json")

  var myScenario: ScenarioBuilder = scenario("My Scenario")
    .exec(http("Request 1").get("/portfolios/12345"))

    init {
      setUp(
        myScenario.injectOpen(constantUsersPerSec(25.0).during(60))
      ).protocols(httpProtocol);
    }
}