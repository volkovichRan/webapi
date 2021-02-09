package com.main.web.main

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

object Main extends App {

  implicit val system = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext = system.executionContext

  val route =
    pathPrefix("api"){
      concat(
        pathSuffix("weather")(weatherRoute),
        pathSuffix("movie")(movieRoute)
      )
    }
  
  val weatherRoute = get {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to weather /h1>"))
  }

  val movieRoute = get {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to movie</h1>"))
  }

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

}
