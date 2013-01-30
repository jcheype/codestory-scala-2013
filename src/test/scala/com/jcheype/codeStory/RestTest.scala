package com.jcheype.codeStory

import enonce2.Vol
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatra.test.scalatest.ScalatraSuite
import java.util.UUID
import util.Random
import collection.mutable


/**
 * Created with IntelliJ IDEA.
 * User: juliencheype
 * Date: 27/5/13
 * Time: 00:21
 * To change this template use File | Settings | File Templates.
 */
class RestTest extends ScalatraSuite with FunSuite with BeforeAndAfter {

  before {
    addServlet(classOf[CodeStoryServlet], "/*")
  }

  test("get email") {
    get("/?q=Quelle+est+ton+adresse+email") {
      status should equal(200)
      body should equal("cheype@gmail.com")
    }
  }
}
