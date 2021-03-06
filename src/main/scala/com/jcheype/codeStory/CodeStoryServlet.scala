package com.jcheype.codeStory

import com.jcheype.codeStory.calc.Calc
import enonce1.Decomposer.FooBar
import enonce1.{FooBarSerializer, Decomposer}
import enonce2.{Path, Optimizer, Vol}
import org.scalatra._
import scalate.ScalateSupport
import org.slf4j.LoggerFactory
import com.google.common.io.Files
import java.io.File
import java.nio.charset.Charset
import com.petebevin.markdown.MarkdownProcessor
import xml.Unparsed
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.fasterxml.jackson.databind.module.SimpleModule
import org.json4s.JsonAST.JValue
import java.text.DecimalFormat

class CodeStoryServlet extends ScalatraServlet with ScalateSupport with JacksonJsonSupport {
  def logger = LoggerFactory.getLogger(classOf[CodeStoryServlet])

  protected implicit val jsonFormats: Formats = DefaultFormats

  val module = new SimpleModule("CodeStory")
  module.addSerializer(classOf[FooBar], new FooBarSerializer())
  mapper.registerModule(module)

  val queries = Map(
    "Quelle est ton adresse email" -> "cheype@gmail.com",
    "Souhaites-tu-participer-a-la-suite-de-Code-Story(OUI/NON)" -> "OUI",
    "Es tu heureux de participer(OUI/NON)" -> "OUI",
    "Es tu abonne a la mailing list(OUI/NON)" -> "OUI",
    "Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)" -> "OUI",
    "Est ce que tu reponds toujours oui(OUI/NON)" -> "NON",
    "As tu bien recu le premier enonce(OUI/NON)" -> "OUI",
    "1,0000000000000000000000000000000000000000000000001*1,0000000000000000000000000000000000000000000000001" -> "1,00000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000001",
    "((1,1 2) 3,14 4 (5 6 7) (8 9 10)*4267387833344334647677634)/2*553344300034334349999000" -> "31878018903828899277492024491376690701584023926880",
    "As tu passe une bonne nuit malgre les bugs de l etape precedente(PAS_TOP/BOF/QUELS_BUGS)" -> "QUELS_BUGS",
    "As tu bien recu le second enonce(OUI/NON)" -> "OUI",
    "As tu copie le code de ndeloof(OUI/NON/JE_SUIS_NICOLAS)" -> "NON")

  before() {
    logger.debug("REQUEST METHOD: " + request.getMethod)
    logger.debug("REQUEST URI: " + request.getRequestURI + request.getQueryString)
    logger.debug("REQUEST PROTOCOL: " + request.getProtocol)
    logger.debug("REQUEST HEAD: " + request.headers)
  }

  get("/") {
    <html>
      <body>CodeStory 2013</body>
    </html>
  }

  get("/", params.contains("q")) {
    val q: String = params("q")
    queries.get(q) match {
      case Some(reply) => reply
      case None =>
        val calc: Number = Calc.calc(Calc.prepareString(q))
        if (calc != null) {
          val df = new DecimalFormat()
          df.setParseIntegerOnly(true)
          df.setGroupingUsed(false)
          df.setMaximumIntegerDigits(200)
          val format: String = df.format(calc)
          format.replaceAll("\\.", ",")
        }
    }
  }

  post("/enonce/:id") {
    val id: Int = params.getOrElse("id", halt(400)).toInt
    logger.debug("Enonce: " + id)
    val file: File = new File("enonce" + id + ".md")
    logger.debug("Enonce: " + file.getAbsolutePath)
    Files.write(request.body, file, Charset.forName("UTF-8"))

    status = 201
    Ok()
  }

  get("/enonce/:id") {
    val id: Int = params.getOrElse("id", halt(400)).toInt
    val m: MarkdownProcessor = new MarkdownProcessor()
    val file: File = new File("enonce" + id + ".md")
    val content = m.markdown(Files.toString(file, Charset.forName("UTF-8")))
    <html>
      <body>
        {Unparsed(content)}
      </body>
    </html>
  }

  get("/scalaskel/change/:value") {
    contentType = formats("json")
    val value: Int = params.getOrElse("value", halt(400)).toInt
    val result = Decomposer.decompositionMap.get(value)

    result match {
      case Some(fooBarSet) => mapper.writeValueAsString(fooBarSet.toArray)
      case None => 400
    }
  }

  post("/jajascript/optimize") {
    contentType = formats("json")

    val body:String = request.getParameterNames.nextElement().toString

    val jsValue = readJsonFromBody(body)

    val field: JValue = jsValue.transformField {
      case ("VOL", x) => ("name", x)
      case ("DEPART", x) => ("depart", x)
      case ("DUREE", x) => ("duree", x)
      case ("PRIX", x) => ("prix", x)
    }


    val vols: List[Vol] = field.extract[List[Vol]]
    val optimize: Path = Optimizer.optimize(vols)

    status = 201
    Optimizer.format(optimize)
  }

  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map {
      path =>
        contentType = "text/html"
        layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }
}
