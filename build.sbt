name := "coding-problem"

version := "1.0.0"

scalaVersion := "2.10.3"

homepage := Some(new URL("https://github.com/shengmin/coding-problem"))

lazy val rangeMinimumQuery = newHackerRankProject(
  "range-minimum-query", Seq(
    version := "1.0.0"
  )
)

lazy val brainF__kInterpreter = newHackerRankProject(
  "brainF__kInterpreter", Seq(
    version := "1.0.0"
  )
)

lazy val root = project.in(file("."))
  .aggregate(rangeMinimumQuery, brainF__kInterpreter)
