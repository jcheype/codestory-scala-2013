package com.jcheype.codeStory.calc

import javax.script.{ScriptEngine, ScriptEngineManager}

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 22/4/13
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */
object Calc {
  private[simpleCalc] var factory: ScriptEngineManager = new ScriptEngineManager
  private[simpleCalc] var engine: ScriptEngine = factory.getEngineByName("groovy")


  def prepareString(s: String): String  = {
    s.replace(' ', '+').replace(',', '.')
  }

  def calc(s: String): Number = {
    val value = engine.eval(s.replaceAll("[a-zA-Z]", ""))
    value.asInstanceOf[Number]
  }
}
