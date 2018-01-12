name := "mr-scala-libs"

version := "2.2.1"

organization := "com.github.mt_sito"

lazy val root = (project in file("."))

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
	"commons-codec" % "commons-codec" % "1.10",
	"org.scalatest" %% "scalatest" % "3.0.2" % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

parallelExecution in test := false

parallelExecution in ThisBuild := false

publishMavenStyle := true

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

crossScalaVersions := Seq(
	"2.10.7",
	"2.11.12",
	scalaVersion.value
)
