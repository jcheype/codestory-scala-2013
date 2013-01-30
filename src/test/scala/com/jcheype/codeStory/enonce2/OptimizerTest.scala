package com.jcheype.codeStory.enonce2

import org.scalatest.FunSuite
import util.Random
import collection.mutable
import java.util.UUID
import org.slf4j.LoggerFactory
import org.scalatest.matchers.ShouldMatchers

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 27/5/13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
class OptimizerTest extends FunSuite with ShouldMatchers {
  def logger = LoggerFactory.getLogger(classOf[OptimizerTest])

  val rand = new Random()

  def time[R](block: => R): R = {
    val t0 = System.currentTimeMillis()
    val result = block
    info("Elapsed time: " + (System.currentTimeMillis - t0) + "ms")
    result
  }

  def randVol(size: Int): List[Vol] = {
    val result = new mutable.HashSet[Vol]()
    for (i <- 0 to size) {
      val vol = new Vol(
        UUID.randomUUID().toString,
        rand.nextInt(10000),
        rand.nextInt(10),
        rand.nextInt(100)
      )
      result.add(vol)
    }

    result.toList
  }

  test("Optimize") {
    Optimizer.optimize(randVol(100000)) // on fait juste chauffer :-)
    Optimizer.optimize(randVol(100000)) // on fait juste chauffer :-)
    Optimizer.optimize(randVol(100000)) // on fait juste chauffer :-)

    val vols: List[Vol] = randVol(100000)
    val optimize: Path = time {
      Optimizer.optimize(vols)
    }
    info("Path GAIN: " + optimize.gain)
    optimize.vols.sliding(2).foreach{
      case List(v1, v2) =>
        v1.end should be <= v2.depart
    }
  }
}
