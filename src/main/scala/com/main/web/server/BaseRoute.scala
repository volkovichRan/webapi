package com.main.web.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpRequest}
import akka.http.scaladsl.server.Directives.{complete, get, onComplete, parameter}
import akka.http.scaladsl.server.Route
import com.main.web.model.Site

import scala.collection.mutable.Map
import scala.util.{Failure, Success}
import com.main.web.utils.FutureTimeout._
import scala.language.postfixOps


abstract class BaseRoute(site: Site)(implicit actorSystem: ActorSystem) {

  val cache = Map[String, Any]()

  val route = get {
    parameter(site.apiPath){
      param =>
        onComplete(Http().singleRequest(HttpRequest(
          uri = createRequestUrl(site, param))) withTimeout){
          case Success(result) =>
            cache += (param -> result)
            printResult(result)
          case Failure(err) =>
            if(cache.get(param).isDefined)
              printResult(cache(param))
            else
              complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>$err</h1>")
              )
        }
    }
  }

  def printResult(result: Any): Route = {
    complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>$result</h1>"))
  }

  def createRequestUrl(site: Site, city: String): String

}
