package me.shengmin.core

import org.scalatest.{Matchers, FlatSpec}
import scala.util.Random

class SegmentTreeSpec extends FlatSpec with Matchers {
  it should "have the expected minimum value" in {
    val numbers = Array(0, 1, 2, 3, 4, 5, 6, 7, 9)
    val tree = new SegmentTree[Int](numbers.size)(
      index => numbers(index),
      (left, right) => math.min(left.value, right.value)
    )

    checkMinimum(numbers, tree)

    val random = new Random
    val randomNumbers = numbers.map(_ => random.nextInt(20))
    for (i <- 0 until randomNumbers.size) {
      tree.update(i)(
        segment => randomNumbers(segment.start),
        (left, right) => math.min(left.value, right.value)
      )
    }

    checkMinimum(randomNumbers, tree)
  }

  private[this] def checkMinimum(numbers: Array[Int], tree: SegmentTree[Int]) {
    for (i <- 0 until numbers.size) {
      for (j <- i + 1 until numbers.size) {
        val min = tree.query(i, j, Int.MaxValue)(
          segment => segment.value,
          (left, right) => math.min(left, right)
        )
        min should be (numbers.slice(i, j).min)
      }
    }
  }
}
