name := "coding-problem"

version := "1.0.0"

scalaVersion := "2.10.3"

homepage := Some(new URL("https://github.com/shengmin/coding-problem"))

lazy val rangeMinimumQuery = project.in(file("hackerrank/range-minimum-query"))

lazy val root = project.in(file("."))
  .aggregate(rangeMinimumQuery)
