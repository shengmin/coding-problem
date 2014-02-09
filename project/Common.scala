import sbt.Keys._
import sbt._

object Common extends Build {

  private[this] def commonSettings = Seq(
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
    scalaSource in Compile := baseDirectory.value,
    javaSource in Compile := baseDirectory.value
  )

  private[this] def newProject(
    category: String,
    name: String,
    settings: Seq[Setting[_]]
  ) = {
    Project(
      id = s"${category}-${name}",
      base = file(s"${category}/${name}")
    ) settings ((commonSettings ++ settings): _*)
  }

  def newHackerRankProject(name: String, settings: Seq[Setting[_]] = Seq.empty)
    = newProject("hackerrank", name, settings)
}
