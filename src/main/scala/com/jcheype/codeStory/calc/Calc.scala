package com.jcheype.codeStory.calc

import javax.script.{ScriptEngine, ScriptEngineManager}
import org.slf4j.LoggerFactory

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 22/4/13
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */
object Calc {
  def logger = LoggerFactory.getLogger(Calc.getClass)

  val factory: ScriptEngineManager = new ScriptEngineManager
  val engine: ScriptEngine = factory.getEngineByName("groovy")


  def prepareString(s: String): String  = {
    s.replace(' ', '+').replace(',', '.').replaceAll("[a-zA-Z]", "")
  }

  def calc(s: String): Number = {
    logger.debug("calc: " + s)
    try{
      val value = engine.eval(s)
      logger.debug("calc result: " + value)

      value.asInstanceOf[Number]
    }catch {
      case e:
        Exception => logger.error("CALC:", e)
      0
    }
  }
}
