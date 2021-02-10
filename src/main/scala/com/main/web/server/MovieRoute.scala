package com.main.web.server

import akka.actor.ActorSystem
import com.main.web.model.Site


class MovieRoute(site: Site)(implicit actorSystem: ActorSystem) extends BaseRoute(site) {

  def createRequestUrl(site: Site, movie: String): String = {
    s"${site.restUri}?i=${movie}&apikey=${site.apiKey}"
  }


}
