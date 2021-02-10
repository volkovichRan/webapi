package com.test.web

import com.main.web.model.{Site, Sites}
import pureconfig.ConfigSource
import pureconfig._
import pureconfig.generic.auto._


class ConfigurationTest extends BaseTest {

  "site1" should " have name site1 and apiKey 1" in {
    testParseSite(Site("site1", "apiKey1", "", ""))
  }

  "site2" should " have name site2 and apiKey 2" in {
    testParseSite(Site("site2", "apiKey2", "", ""))
  }

  private def testParseSite(site: Site): Unit ={
    val siteResult = ConfigSource.default.load[Sites].right.get.sites.find(_.siteName == site.siteName).get
    assertResult(site.apiKey)(siteResult.apiKey)
  }

}
