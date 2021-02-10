package com.main.web.server

import akka.actor.ActorSystem
import com.main.web.model.Site



class WeatherRoute(site: Site)(implicit actorSystem: ActorSystem) extends BaseRoute(site) {

  def createRequestUrl(site: Site, city: String): String = {
    s"${site.restUri}?q=${city}&APPID=${site.apiKey}"
  }

}
