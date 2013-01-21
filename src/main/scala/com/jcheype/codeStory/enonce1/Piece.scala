package com.jcheype.codeStory.enonce1

/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 21/4/13
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
sealed abstract class Piece(val value: Int)

case object FOO extends Piece(1)
case object BAR extends Piece(7)
case object QIX extends Piece(11)
case object BAZ extends Piece(21)

