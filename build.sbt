name := "webApi"

version := "0.1"

scalaVersion := "2.13.4"
val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.3"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"
libraryDependencies += "com.typesafe" % "config" % "1.4.1"
libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.14.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
)

