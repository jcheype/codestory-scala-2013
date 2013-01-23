package com.jcheype.codeStory.enonce2

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 22/4/13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
class Path(val vols:Set[Vol], val gain:Int) {

  override def toString(): String = {
    vols.toString()
  }
}
