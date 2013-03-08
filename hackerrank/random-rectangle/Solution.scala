import java.io._
import java.util.{StringTokenizer, BitSet}
import scala.collection.mutable.{HashMap, MutableList, Map}

object Solution {
  val reader = new BufferedReader(new InputStreamReader(System.in))
  val N = reader.readLine().trim().toInt
  val sticks = new Array[Int](N)
  val tokenizer = new StringTokenizer(reader.readLine())
  
  for (i <- 0 until N) {
    sticks(i) = tokenizer.nextToken().toInt
  }
  
  val R = reader.readLine().trim().toInt
  val widthTable = HashMap.empty[Long, MutableList[(Int, Int, Double)]]
  val heightTable = HashMap.empty[Long, MutableList[(Int, Int, Double)]]
  
  def emptyList = MutableList.empty[(Int, Int, Double)]
  
  def log[E](a: Array[Array[E]]) {
    for (row <- a) {
      for (i <- row) {
        print(i)
        print(' ')
      }
      println()
    }
  }
  
  def solveWidthStage(
      usedStickSet: BitSet, 
      width: Int, 
      height: Int,
      probability: Double): MutableList[(Int, Int, Double)] = {
      
    val key = usedStickSet.toLongArray()(0)
    return widthTable.get(key) match {
      case Some(x) => x
      case None => {
        if (width > R) {
          return emptyList
        }
    
        val stickId = usedStickSet.nextClearBit(0)
        if (stickId >= N) {
          return MutableList((width, height, probability))
        }
        
        val list = emptyList
        val newWidth = width + sticks(stickId)
        val newUsedStickSet = usedStickSet.clone().asInstanceOf[BitSet]
        val newProbability = probability * 0.25D
        newUsedStickSet.set(stickId)
        
        // Take the stick, and stay
        list ++= solveWidthStage(newUsedStickSet, newWidth, height, newProbability)
        
        // Take the stick, and go to height stage
        list ++= solveHeightStage(newUsedStickSet.clone().asInstanceOf[BitSet], newWidth, height, newProbability)
        
        // Eat the stick, and stay
        list ++= solveWidthStage(newUsedStickSet.clone().asInstanceOf[BitSet], width, height, newProbability)
        
        // Eat the stick, and go to height stage
        list ++= solveHeightStage(newUsedStickSet.clone().asInstanceOf[BitSet], width, height, newProbability)
        
        widthTable(key) = list
        list
      }
    }
  }
  
  def solveHeightStage(usedStickSet: BitSet, width: Int, height: Int, probability: Double): MutableList[(Int, Int, Double)] = {
    val key = usedStickSet.toLongArray()(0)
    return heightTable.get(key) match {
      case Some(x) => x
      case None => {
        if (height > R || width > R) {
          return emptyList
        }
      
        val stickCount = N - usedStickSet.cardinality()
        if (stickCount == 0) {
          return MutableList((width, height, probability))
        }
        
        val list = emptyList
        var stickId = usedStickSet.nextClearBit(0)
        
        while (stickId < N) {
          val newUsedStickSet = usedStickSet.clone().asInstanceOf[BitSet]
          newUsedStickSet.set(stickId)
          
          list ++= solveHeightStage(newUsedStickSet, width, height + sticks(stickId), probability / stickCount)
          
          stickId = usedStickSet.nextClearBit(stickId + 1)
        }
        
        heightTable(key) = list
        list
      }
    }
  }
  
  
  def solveWidthStage(usedSticks: BitSet, stickId: Int): MutableList[(Int, Int, Double)] = {
    return widthTable.get(stickId) match {
      case Some(x) => x
      case None => {
        if (stickId >= N) return MutableList((0, 0, 1.0D))
      
        val list = MutableList.empty[(Int, Int, Double)]
        val nextStickId = stickId + 1
        val usedSticks1 = usedSticks.clone().asInstanceOf[BitSet]
        usedSticks1.set(stickId)
        val heightList = solveHeightStage(usedSticks1, N - nextStickId)
        val widthList = solveWidthStage(usedSticks1.clone().asInstanceOf[BitSet], nextStickId)
         
        // The robot eats the stick and stays at width stage
        for ((w, h, p) <- widthList) {
          if (w <= R) list += ((w, h, p * 0.25D))
        }
        
        // The robot eats the stick and goes to height stage
        for ((w, h, p) <- heightList) {
          if (w <= R) list += ((w, h, p * 0.25D))
        }
        
        // The robot takes the stick and stays at width stage
        for ((w, h, p) <- widthList) {
          val newWidth = w + sticks(stickId);
          if (newWidth <= R) list += ((newWidth, h, p * 0.25D))
        }
        
        // The robot takes the stick and goes to height stage
        for ((w, h, p) <- heightList) {
          val newWidth = w + sticks(stickId);
          if (newWidth <= R) list += ((newWidth, h, p * 0.25D))
        }
        
        widthTable(stickId) = list
        list
      }
    }
  }
  
  def solveHeightStage(usedSticks: BitSet, stickCount: Int): MutableList[(Int, Int, Double)] = {
    val key = usedSticks.toLongArray()(0)
    return heightTable.get(key) match {
      case Some(x) => x
      case None => {
        if (stickCount == 0) return MutableList((0, 0, 1.0D))
      
        val stickId = usedSticks.nextClearBit(0)
        val list = MutableList.empty[(Int, Int, Double)]
        
        var i = stickId
        while (i < N) {
          val newUsedSticks = usedSticks.clone().asInstanceOf[BitSet]
          newUsedSticks.set(i)
          
          for ((w, h, p) <- solveHeightStage(newUsedSticks, stickCount - 1)) {
            val newHeight = h + sticks(i)
            if (newHeight <= R) list += ((w, newHeight, p / stickCount))
          }
          
          i = usedSticks.nextClearBit(i + 1)
        }
        
        heightTable(key) = list
        list
      }
    }
  }
  
  def main(a: Array[String]) {
    var area = 0.0D
    val usedStickSet = new BitSet(N)
    usedStickSet.set(N)
    val list = solveWidthStage(usedStickSet, 0, 0, 1.0D)
    for ((w, h, p) <- list) {
      area = area + w * h * p
    }
    println(area)
    println()
    println(list)
    println()
    println(widthTable)
    println()
    println(heightTable)
  }
}
