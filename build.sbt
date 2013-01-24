organization := "com.jcheype"

name := "CodeStory 2013"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

seq(webSettings :_*)

classpathTypes ~= (_ + "orbit")

libraryDependencies ++= Seq(
  "org.scalatra" % "scalatra" % "2.2.0-RC2",
  "org.scalatra" % "scalatra-scalate" % "2.2.0-RC2",
  "org.scalatra" % "scalatra-specs2" % "2.2.0-RC2" % "test",
  "org.scalatra" % "scalatra-json" % "2.2.0-RC2",
  "org.json4s"   %% "json4s-jackson" % "3.0.0",
  "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container;test",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
)

libraryDependencies += "com.google.guava" % "guava" % "13.0.1"

libraryDependencies += "org.markdownj" % "markdownj" % "0.3.0-1.0.2b4"

libraryDependencies += "org.mvel" % "mvel2" % "2.1.3.Final"

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

libraryDependencies += "org.codehaus.groovy" % "groovy" % "1.8.4"


port in container.Configuration := 8081
org.mortbay.jetty.Request.maxFormContentSize