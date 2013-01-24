package com.jcheype.codeStory.enonce2

import collection.mutable
import org.slf4j.LoggerFactory

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 22/4/13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
object Optimizer {
  def logger = LoggerFactory.getLogger(Optimizer.getClass)


  val emptyPath = new Path(Set[Vol](), 0)




//  def length[A](list: List[A]): Int = list match {
//    case Nil => 0
//    case _ :: tail => 1 + length(tail)
//  }

  def bestWay(idx:Int, cache: mutable.Map[Int, Path], volList:List[Vol], last:Path = emptyPath): Path = {
    volList match {
      case Nil => last

      case vol :: tail =>
        if (vol.depart != idx){
          cache.put(idx, last)
          return bestWay(idx-1, cache, volList, last)
        }

        val path = cache.get(vol.end).getOrElse(emptyPath)
        val newPath = new Path(Set(vol) ++ path.vols, path.gain + vol.prix)
        if (newPath.gain > last.gain)
          bestWay(idx, cache, tail, newPath)
        else
          bestWay(idx, cache, tail, last)

    }
  }

  def optimize(vols: Set[Vol]): Path = {
    val cache: mutable.Map[Int, Path] = new mutable.HashMap[Int, Path]()

    val volList: List[Vol] = vols.toList.sortWith(_.depart > _.depart)

    val maxTime = volList.head.depart
    var last = emptyPath

    bestWay(maxTime, cache, volList, last)

  }

  def format(path:Path):String = {
    val template: String = "{\n" + "    \"gain\" : %d,\n" + "    \"path\" : [%s]\n" + "}"
    val pathString: String = path.vols.map("\"" + _.name + "\"").mkString(", ")
    template.format(path.gain, pathString)
  }
}
