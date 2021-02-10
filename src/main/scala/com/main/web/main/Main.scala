package com.main.web.main

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import com.main.web.server.RestApi

object Main extends App {

  implicit val system = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext = system.executionContext


  val restApi = new RestApi("api", 8080)(executionContext, system.classicSystem)


}
