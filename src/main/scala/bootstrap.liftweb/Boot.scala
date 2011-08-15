package bootstrap.liftweb

import net.liftweb.sitemap.{Menu, SiteMap}
import net.liftweb.http.{Html5Properties, Req, LiftRules}

/**
 * @author il
 * @version 8/16/11 1:04 AM
 */

class Boot {
  def boot {
    LiftRules.addToPackages("iron9light.sandbox.cometSessionDestory")

    def siteMap() = SiteMap(
      Menu.i("Home") / "index"
    )

    LiftRules.setSiteMapFunc(siteMap _)

    LiftRules.htmlProperties.default.set((req: Req) => new Html5Properties(req.userAgent))

    LiftRules.dispatch.append(NewSession)
  }
}

import net.liftweb._
import http._
import rest._
import iron9light.sandbox.cometSessionDestory.comet._

object NewSession extends RestHelper {
  serve {
    case "newsession" :: Nil Get _ =>
  
      S.session.open_!.destroySessionAndContinueInNewSession {
        () => {
          buttonValue.set("Killed")
          S.redirectTo("/")
        }
      }

  }
}
