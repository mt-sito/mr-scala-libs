import sbt._
import Keys._


object ApplicationBuild extends Build {
	val appName = "mr-scala-libs"
	val appVersion = "2.2.1"
	val appOrganization = "com.github.mt_sito"
	val buildScalaVersion = "2.12.4"

	lazy val root = Project(id = appName,
		base = file("."),
		settings = Project.defaultSettings ++ Seq(
			name := appName,
			organization := appOrganization,
			version := appVersion,
			scalaVersion := buildScalaVersion,
			publishMavenStyle := true,
			publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository"))),

			crossScalaVersions := Seq(
				"2.10.7",
				"2.11.12",
				buildScalaVersion
			),

			libraryDependencies ++= Seq(
				"commons-codec" % "commons-codec" % "1.10",
				"org.scalatest" %% "scalatest" % "3.0.2" % "test"
			)
		)
	)
}
