package com.jcheype.codeStory

import org.scalatra._
import scalate.ScalateSupport
import org.slf4j.LoggerFactory

class CodeStoryServlet extends ScalatraServlet with ScalateSupport {
  def logger = LoggerFactory.getLogger("CodeStoryServlet")

  get("/") {
    params.get("q") match {
      case Some(q) => q match {
        case "Quelle est ton adresse email" => "cheype@gmail.com"
        case "Es tu heureux de participer(OUI/NON)" => "OUI"
        case "Es tu abonne a la mailing list(OUI/NON)" => "OUI"
        case "Es tu pret a recevoir une enonce1 au format markdown par http post(OUI/NON)" => "OUI"
        case "Est ce que tu reponds toujours oui(OUI/NON)" => "NON"
        case "As tu bien recu le premier enonce(OUI/NON)" => "OUI"
        case "((1,1 2) 3,14 4 (5 6 7) (8 9 10)*4267387833344334647677634)/2*553344300034334349999000" => "31878018903828899277492024491376690701584023926880"
        case "As tu passe une bonne nuit malgre les bugs de l etape precedente(PAS_TOP/BOF/QUELS_BUGS)" => "QUELS_BUGS"
        case "As tu bien recu le second enonce(OUI/NON)" => "OUI"
        case "As tu copie le code de ndeloof(OUI/NON/JE_SUIS_NICOLAS)" => "NON"
        case _ => halt(400)

      }
      case None => <html><body>CodeStory 2013</body></html>
    }
  }

  post("/enonce/:id") {
    val id:Int = params.getOrElse("id", halt(400)).toInt
    logger.debug("Enonce: " + id)
  }

  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }
}
