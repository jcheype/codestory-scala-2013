package com.jcheype.codeStory

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
    val vols: List[Vol] = randVol(50000)
    val start = System.currentTimeMillis()
    val optimize: Path = Optimizer.optimize(vols)
    logger.debug("SET: " + vols)
    logger.debug("PATH: " + Optimizer.format(optimize))
    logger.debug("duration: " + (System.currentTimeMillis() - start))
  }


  def randVol(size:Int): List[Vol] = {
    val result = new mutable.HashSet[Vol]()
    for (i <- 0 to size){
      val vol = new Vol(
        UUID.randomUUID().toString,
        rand.nextInt(1000),
        rand.nextInt(10),
        rand.nextInt(100)
      )
      result.add(vol)
    }

    result.toList
  }
}
