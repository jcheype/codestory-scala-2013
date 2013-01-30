package com.jcheype.codeStory.enonce2

import collection.mutable
import org.slf4j.LoggerFactory
import annotation.tailrec

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 22/4/13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
object Optimizer {
  def logger = LoggerFactory.getLogger(Optimizer.getClass)


  val emptyPath = new Path(List[Vol](), 0)

  @tailrec
  private def bestWay(idx: Int, cache: mutable.Map[Int, Path], volList: List[Vol], last: Path = emptyPath): Path = {
    volList match {
      case Nil => last

      case vol :: tail if (vol.depart != idx) =>
        bestWay(idx - 1, cache, volList, last)

      case vol :: tail =>
        val path = cache.get(vol.end).getOrElse(emptyPath)
        val newPath = new Path(List(vol) ++ path.vols, path.gain + vol.prix)
        val best = if (newPath.gain > last.gain) newPath else last
        bestWay(idx, cache, tail, best)

    }
  }

  def optimize(vols: List[Vol]): Path = {
    val cache: mutable.Map[Int, Path] = new mutable.HashMap[Int, Path]()

    val volList: List[Vol] = vols.toList.sortWith(_.depart > _.depart)

    val maxTime = volList.head.depart

    bestWay(maxTime, cache, volList, emptyPath)

  }

  def format(path: Path): String = {
    val template: String = "{\n" + "    \"gain\" : %d,\n" + "    \"path\" : [%s]\n" + "}"
    val pathString: String = path.vols.toList.reverse.map("\"" + _.name + "\"").mkString(", ")
    template.format(path.gain, pathString)
  }
}
