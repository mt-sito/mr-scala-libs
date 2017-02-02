import sbt._
import Keys._


object ApplicationBuild extends Build {
	val appName = "mr-scala-libs"
	val appVersion = "2.0.0"
	val appOrganization = "com.github.mt_sito"
	val buildScalaVersion = "2.12.1"

	lazy val root = Project(id = appName,
		base = file("."),
		settings = Project.defaultSettings ++ Seq(
			name := appName,
			organization := appOrganization,
			version := appVersion,
			scalaVersion := buildScalaVersion,
			publishMavenStyle := true,
			otherResolvers := Seq(Resolver.file("dotM2", file(Path.userHome + "/.m2/repository"))),
			publishLocalConfiguration <<= (packagedArtifacts, deliverLocal, ivyLoggingLevel) map {
				(arts, _, level) => new PublishConfiguration(None, "dotM2", arts, List[String]("sha1", "md5"), level)
			},

			crossScalaVersions := Seq(
				"2.10.6",
				"2.11.8",
				"2.12.1"
			),

			libraryDependencies ++= Seq(
				"commons-codec" % "commons-codec" % "1.10",
				"org.scalatest" %% "scalatest" % "3.0.1" % "test"
			)
		)
	)
}
