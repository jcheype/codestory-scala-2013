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


  val emptyPath = new Path(Set[Vol]())

  def bestWay(cache: mutable.Map[Int, Path], it:Iterator[Vol], last:Path = emptyPath): Path = {
    if (!it.hasNext)
      return last
    val vol = it.next()

    val path = cache.get(vol.end).getOrElse(new Path(Set[Vol]()))
    val newPath = new Path(Set(vol) ++ path.vols)


    if (newPath.gain > last.gain)
      bestWay(cache, it, newPath)
    else
      bestWay(cache, it, last)
  }

  def optimize(vols: Set[Vol]): Path = {
    val cache: mutable.Map[Int, Path] = new mutable.HashMap[Int, Path]()

    var volList: List[Vol] = vols.toList.sortWith(_.depart > _.depart)

    val maxTime = volList.head.depart
    var last = emptyPath

    for (i <- maxTime to 0 by -1) {
      val takeWhile = volList.takeWhile(_.depart == i)
      volList = volList.takeRight(volList.length-takeWhile.length)
      last = bestWay(cache, takeWhile.iterator, last)
      cache.put(i, last)
    }

    last
  }

  def format(path:Path):String = {
    val template: String = "{\n" + "    \"gain\" : %d,\n" + "    \"path\" : %s\n" + "}"
    val pathString: String = path.vols.map("\"" + _.name + "\"").mkString(",")
    template.format(path.gain, pathString)
  }
}
