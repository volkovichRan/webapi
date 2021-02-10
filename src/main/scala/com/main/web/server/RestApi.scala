package com.main.web.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{concat, pathPrefix, pathSuffix}
import com.main.web.model.Site

import scala.concurrent.ExecutionContext

class RestApi(baseApi: String, port: Int)(implicit executionContext: ExecutionContext, actorSystem: ActorSystem){

  val weatherSite = Site("https://openweathermap.org/api", "c2152ce33eec94f628bcb40cda3da446",
    "http://api.openweathermap.org/data/2.5/weather", "city")

  val movieSite = Site("http://www.omdbapi.com/", "dce24c91", "http://www.omdbapi.com/", "movie")

  val route = pathPrefix(baseApi){
    concat(
      pathSuffix("weather")(new WeatherRoute(weatherSite).route),
      pathSuffix("movie")(new MovieRoute(movieSite).route)
    )
  }

  val bindingFuture = Http().newServerAt("localhost", port).bind(route)


}
