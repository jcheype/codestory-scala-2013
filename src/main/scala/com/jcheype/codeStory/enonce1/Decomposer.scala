package com.jcheype.codeStory.enonce1

import collection.mutable
import org.slf4j.{Logger, LoggerFactory}
import com.jcheype.codeStory.enonce1.FOO

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 21/4/13
 * Time: 23:21
 * To change this template use File | Settings | File Templates.
 */
object Decomposer {
  val logger = LoggerFactory.getLogger("Decomposer")

  val decompositionMap:mutable.Map[Int, mutable.Set[FooBar]] = mutable.HashMap()

//  Decomposer(int to) {
//    decompositionMap.put(0, Collections.EMPTY_SET);
//
//    for(int i=1; i<to+1; i++){
//      Set<FooBar> decomposeList = new HashSet<FooBar>();
//      for(Piece p : Piece.values()){
//        decomposeList.addAll(getDecomposeList(i, p, decompositionMap));
//      }
//      decompositionMap.put(i, decomposeList);
//    }
//  }

  def getDecomposeList(value:Int, piece:Piece):Set[FooBar] = {
    //logger.debug(String.format("value[%s] piece[%s]", value, piece));
    if(value < piece.value)
      return Set()

    val result = new mutable.HashSet[FooBar]()

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
  class FooBar(val foo:Int, val bar:Int, val qix:Int, val baz:Int )
}
