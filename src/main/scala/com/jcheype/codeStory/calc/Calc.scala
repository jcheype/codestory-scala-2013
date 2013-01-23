package com.jcheype.codeStory.calc

import org.mvel2.MVEL

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 22/4/13
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */
object Calc {

  def prepareString(s: String): String  = {
    s.replace(' ', '+').replace(',', '.')
  }

  def calc(s: String): Number = {
    val value = MVEL.eval(s)
    value.asInstanceOf[Number]
  }
}
