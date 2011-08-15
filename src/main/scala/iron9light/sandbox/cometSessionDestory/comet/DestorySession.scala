package iron9light.sandbox.cometSessionDestory.comet

import net.liftweb.http.{SessionVar, S, CometActor}
import net.liftweb.util.JsonCommand
/**
 * @author il
 * @version 8/16/11 1:15 AM
 */

class DestorySession extends CometActor {

  private object buttonValue extends SessionVar[String]("Kill")

  def render = "button" #> <button onclick={jsonSend("killsession").toJsCmd}>
    {buttonValue.get}
  </button>

  override val autoIncludeJsonCode = true

  override def receiveJson = {
    case JsonCommand("killsession", _, _) =>
      S.session.open_!.destroySessionAndContinueInNewSession {
        () => {
          buttonValue("Killed")
          S.redirectTo("/")
        }
      }
  }
}