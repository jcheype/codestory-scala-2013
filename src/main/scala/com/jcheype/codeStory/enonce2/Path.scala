package com.jcheype.codeStory.enonce2

import tools.nsc.io.Path

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 22/4/13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */
class Path(var vols:Set[Vol]) {
  var gain: Int=vols.aggregate(0)((x, vol) => x + vol.prix, _ + _)

  override def toString(): String = {
    vols.toString()
  }
}
