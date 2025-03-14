# springReactiveDemo
## general
This project was created to demonstrate how `reactive` can safe an application from becoming unresponsive due to heavy load and slow backends.
There are three versions availabe:
  1. `demo` containing a simple spring-web application which calls another rest-api and a database
  2. `demoWebflux`, the same application using spring-webflux and r2dbc instead of spring-web and jdbc
  3. `demoCoroutines`, again the same appication utilizing kotlin coroutines instead of `reactors`

In all three versions there is a docker compose file to ramp-up all needed systems to simulate and monitor the load. This includes:
  1. MySql, the database being used
  2. Wiremock, to simulate the rest-api
  3. prometheus, for metrics collection
  4. grafana, for visualization

## projects structures
...
