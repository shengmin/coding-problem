package me.shengmin.core

import me.shengmin.core.SegmentTree.{ReadOnlySegment, Segment}

class SegmentTree[T](size: Int)(initializer: Int => T, aggregator: (ReadOnlySegment[T], ReadOnlySegment[T]) => T) {

  private[core] val segments = {
    val height = math.ceil(math.log(size) / math.log(2)).toInt
    val capacity = 2 * math.pow(2, height).toInt
    new Array[Segment[T]](capacity)
  }

  initialize(0, 0, size)

  private[this] def leftChildIndex(index: Int) = 2 * index + 1

  private[this] def rightChildIndex(index: Int) = 2 * index + 2

  private[this] def initialize(index: Int, start: Int, end: Int) {
    if (start >= end) return
    if (end - start == 1) {
      val value = initializer(start)
      segments(index) = Segment(start, end, value)
    } else {
      val leftIndex = leftChildIndex(index)
      val rightIndex = rightChildIndex(index)
      val midIndex = start + (end - start) / 2
      initialize(leftIndex, start, midIndex)
      initialize(rightIndex, midIndex, end)
      val value = aggregator(segments(leftIndex), segments(rightIndex))
      segments(index) = Segment(start, end, value)
    }
  }

  def update(itemIndex: Int)
        (base: ReadOnlySegment[T] => T, aggregator: (ReadOnlySegment[T], ReadOnlySegment[T], ReadOnlySegment[T]) => T): this.type = {
    update(0, itemIndex, base, aggregator)
    return this
  }

  private[this] def update(
    index: Int,
    itemIndex: Int,
    base: ReadOnlySegment[T] => T,
    aggregator: (ReadOnlySegment[T], ReadOnlySegment[T], ReadOnlySegment[T]) => T
  ) {
    val segment = segments(index)
    if (segment == null) return
    if (segment.start == itemIndex && (segment.end - segment.start) == 1) {
      segment.value = base(segment)
      return
    }

    val leftIndex = leftChildIndex(index)
    val rightIndex = rightChildIndex(index)
    val left = segments(leftIndex)
    val right = segments(rightIndex)

    if (itemIndex < right.start) {
      update(leftIndex, itemIndex, base, aggregator)
    } else {
      update(rightIndex, itemIndex, base, aggregator)
    }

    segment.value = aggregator(segment, left, right)
  }

  def query[R](start: Int, end: Int, defaultValue: => R)(base: ReadOnlySegment[T] => R, aggregator: (R, R) => R): R = {
    query(0, start, end, defaultValue, base, aggregator)
  }

  private[this] def query[R](
    index: Int,
    start: Int,
    end: Int,
    defaultValue: R,
    base: Segment[T] => R,
    aggregator: (R, R) => R
  ): R = {
    if (start >= end) return defaultValue
    val segment = segments(index)
    if (segment == null) return defaultValue
    if (segment.start == start && segment.end == end) {
      return base(segment)
    }

    val leftIndex = leftChildIndex(index)
    val rightIndex = rightChildIndex(index)
    val left = segments(leftIndex)
    val right = segments(rightIndex)

    if (end < right.start) {
      return query(leftIndex, start, end, defaultValue, base, aggregator)
    } else if (start > left.end) {
      return query(rightIndex, start, end, defaultValue, base, aggregator)
    } else {
      val leftResult = query(leftIndex, start, left.end, defaultValue, base, aggregator)
      val rightResult = query(rightIndex, right.start, end, defaultValue, base, aggregator)
      return aggregator(leftResult, rightResult)
    }
  }
}

object SegmentTree {

  trait ReadOnlySegment[+T] {
    def start: Int

    def end: Int

    def value: T
  }

  private[core] case class Segment[T](val start: Int, val end: Int, var value: T) extends ReadOnlySegment[T]

}
