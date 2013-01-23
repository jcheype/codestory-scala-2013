package com.jcheype.codeStory.enonce2

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 22/4/13
 * Time: 22:20
 * To change this template use File | Settings | File Templates.
 */
case class Vol(name:String, depart:Int, duree:Int, prix:Int) {
  def end: Int = { depart+duree}
}

object DepartOrdering extends Ordering[Vol] {
  def compare(a:Vol, b:Vol) = a.depart compare b.depart
}