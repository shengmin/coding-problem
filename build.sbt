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
  "brain-f__k-interpreter", Seq(
    version := "1.1.0"
  )
)


lazy val leetCodeCommonProject = newLeetCodeProject("common")
lazy val combinationSum = newLeetCodeProject("combination-sum")
lazy val copyListWithRandomPointer =
  newLeetCodeProject("copy-list-with-random-pointer").dependsOn(leetCodeCommonProject)

lazy val root = project.in(file("."))
  .aggregate(
    rangeMinimumQuery,
    brainF__kInterpreter,
    combinationSum,
    copyListWithRandomPointer,
    leetCodeCommonProject
  )
