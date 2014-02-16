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

lazy val common = project
  .in(file("common"))
  .settings((commonSettings ++ Seq(
    version := "1.0.0"
  )): _*)
lazy val leetCodeCommon = newLeetCodeProject("common")
lazy val combinationSum = newLeetCodeProject("combination-sum")
lazy val copyListWithRandomPointer =
  newLeetCodeProject("copy-list-with-random-pointer").dependsOn(leetCodeCommon)
lazy val numberOfBinarySearchTree =
  newHackerRankProject("number-of-binary-search-tree").dependsOn(common)
lazy val cuttingBoards = newHackerRankProject("cutting-boards").dependsOn(common)
lazy val sherlockAndWatson = newHackerRankProject("sherlock-and-watson").dependsOn(common)
lazy val makeItAnagram = newHackerRankProject("make-it-anagram").dependsOn(common)
lazy val bikeRacers = newHackerRankProject("bike-racers").dependsOn(common)
lazy val libraryQuery = newHackerRankProject("library-query").dependsOn(common)

lazy val root = project.in(file("."))
  .aggregate(
    common,
    rangeMinimumQuery,
    brainF__kInterpreter,
    combinationSum,
    copyListWithRandomPointer,
    leetCodeCommon,
    bikeRacers,
    libraryQuery
  )
