import sbt.Keys._
import sbt._

object Common extends Build {

  lazy val commonSettings = Defaults.defaultSettings ++ Seq(
    crossPaths := false,
    fork in run := true,
    scalaVersion := "2.10.3",
    scalacOptions := Seq(
      // Turn on all warnings
      "-feature",
      "-deprecation",
      "-unchecked",
      "-target:jvm-1.6",
      "-encoding", "utf8"
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
    )
  )

  private[this] def newProject(
    category: String,
    name: String,
    settings: Seq[Setting[_]] = Seq.empty
  ) = {
    Project(
      id = s"${category}-${name}",
      base = file(s"${category}/${name}"),
      settings = commonSettings ++ Seq(
        scalaSource in Compile := baseDirectory.value,
        javaSource in Compile := baseDirectory.value
      ) ++ settings
    )
  }

  def newHackerRankProject(name: String, settings: Seq[Setting[_]] = Seq.empty)
    = newProject("hackerrank", name, settings)

  def newLeetCodeProject(name: String) = newProject("leetcode", name, Seq.empty)

  def newCodilityProject(name: String) = newProject("codility", name, Seq())
}
