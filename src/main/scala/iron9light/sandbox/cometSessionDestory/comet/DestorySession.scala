package iron9light.sandbox.cometSessionDestory.comet

import net.liftweb.http.{SessionVar, S, CometActor}
import net.liftweb.util.JsonCommand
import net.liftweb._
import http._
import js._
import JE._
import JsCmds._

/**
 * @author il
 * @version 8/16/11 1:15 AM
 */

object buttonValue extends SessionVar[String]("Kill")

class DestorySession extends CometActor {
  def render = "button" #> <button onclick={jsonSend("killsession").toJsCmd}>
    {buttonValue.get+" "+uniqueId}
  </button>

  override val autoIncludeJsonCode = true

  override def receiveJson = {
    case JsonCommand("killsession", _, _) =>
      partialUpdate(RedirectTo("/newsession"))
      /*
      S.session.open_!.destroySessionAndContinueInNewSession {
        () => {
          buttonValue("Killed")
          S.redirectTo("/")
        }
      }
      */
  }
}
