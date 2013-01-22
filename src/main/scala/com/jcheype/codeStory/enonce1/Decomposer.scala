package com.jcheype.codeStory.enonce1

import collection.mutable
import org.slf4j.{Logger, LoggerFactory}
import collection.immutable.HashSet

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 21/4/13
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
object Decomposer {
  val logger = LoggerFactory.getLogger(Decomposer.getClass)

  val decompositionMap:mutable.Map[Int, Set[FooBar]] = mutable.HashMap()

  init(100)

  def init(max:Int) {
    decompositionMap.put(0, Set[FooBar]());

    for(i <- 1 to max){
      var decomposeList:Set[FooBar]  = HashSet[FooBar]();
      for(p <- List(FOO, BAR, QIX, BAZ)){
        decomposeList = decomposeList ++ (getDecomposeList(i, p));
      }
      decompositionMap.put(i, decomposeList);
    }
  }

  def getDecomposeList(value:Int, piece:Piece):Set[FooBar] = {
    logger.trace("value["+ value +"] piece[" + piece + "]")

    if(value < piece.value)
      return Set()

    val result = mutable.HashSet[FooBar]()

    if(value == piece.value){
      val fooBar = FooBar.build(piece)
      result.add(fooBar)
    }

    decompositionMap(value - piece.value).foreach(foobar => result.add(FooBar.build(foobar,piece)))
    result.toSet


  }

  object FooBar {
    def build(piece:Piece):FooBar = {
      build(new FooBar(0,0,0,0), piece)
    }

    def build(foobar:FooBar, piece:Piece):FooBar = {
      piece match{
        case FOO => new FooBar(1+foobar.foo,0+foobar.bar,0+foobar.qix,0+foobar.baz)
        case BAR => new FooBar(0+foobar.foo,1+foobar.bar,0+foobar.qix,0+foobar.baz)
        case QIX => new FooBar(0+foobar.foo,0+foobar.bar,1+foobar.qix,0+foobar.baz)
        case BAZ => new FooBar(0+foobar.foo,0+foobar.bar,0+foobar.qix,1+foobar.baz)
      }
    }
  }
  case class FooBar (val foo:Int, val bar:Int, val qix:Int, val baz:Int )




}
