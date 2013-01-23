package com.jcheype.codeStory

import com.jcheype.codeStory.calc.Calc
import enonce2.{Path, Optimizer, Vol}
import org.specs2.mutable.Specification
import collection.mutable
import java.util.UUID
import util.Random
import org.slf4j.LoggerFactory

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 22/4/13
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */
class CodeStorySpecs extends Specification {
  def logger = LoggerFactory.getLogger(classOf[CodeStorySpecs])

  val rand = new Random()
  val codeStory = new CodeStoryServlet()

  "1+1 should return 2" in {
    Calc.calc("1+1") must be equalTo(2)
  }

  "(2*3)/4 should return 1.5" in {
    Calc.calc("(2*3)/4") must be equalTo((2F*3)/4)
  }

//  "bla1 " in {
//    val vols: Set[Vol] = Set(
//      Vol("MONAD42",  0, 5, 10),
//      Vol("META18",   3, 7, 14),
//      Vol("LEGACY01", 5, 9, 8),
//      Vol("YAGNI17",  5, 9, 7)
//    )
//    val optimize: Path = Optimizer.optimize(vols)
//    logger.debug("SET: " + vols)
//    logger.debug("PATH: " + optimize)
//    optimize.gain must be equalTo(18)
//
//  }

  "bla " in {
    val vols: Set[Vol] = randVol(50000)
    val optimize: Path = Optimizer.optimize(vols)
    logger.debug("SET: " + vols)
    logger.debug("PATH: " + optimize)
  }


  def randVol(size:Int): Set[Vol] = {
    val result = new mutable.HashSet[Vol]()
    for (i <- 0 to size){
      val vol = new Vol(
        UUID.randomUUID().toString,
        rand.nextInt(10),
        rand.nextInt(10),
        rand.nextInt(10)
      )
      result.add(vol)
    }

    result.toSet
  }
}
