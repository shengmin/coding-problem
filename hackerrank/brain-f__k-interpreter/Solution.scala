import java.io._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Solution {
  val writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))

  case class Environment(val input: String) {
    var memoryIndex = 0
    var instructionIndex = 0
    var inputIndex = 0
    var executionCount = 0
    val memory = mutable.Map.empty[Int, Int]
    val instructions = ArrayBuffer.empty[Instruction]
  }

  trait Instruction {
    protected def nextInstruction(env: Environment) = {
      env.executionCount += 1
      if (env.instructionIndex >= env.instructions.size) null
      else if (env.executionCount >= 100000) {
        writer.println()
        writer.print("PROCESS TIME OUT. KILLED!!!")
        null
      }
      else env.instructions(env.instructionIndex)
    }

    protected def updateEnvironment(env: Environment)

    def execute(env: Environment) = {
      updateEnvironment(env)
      nextInstruction(env)
    }
  }

  object RightArrow extends Instruction {
    override protected def updateEnvironment(env: Environment) {
      env.memoryIndex += 1
      env.instructionIndex += 1
    }
  }

  object LeftArrow extends Instruction {
    override protected def updateEnvironment(env: Environment) {
      env.memoryIndex -= 1
      env.instructionIndex += 1
    }
  }

  object Plus extends Instruction {
    override protected def updateEnvironment(env: Environment) {
      val value = env.memory.getOrElse(env.memoryIndex, 0)
      env.memory.put(env.memoryIndex,
        if (value == 255) 0
        else value + 1
      )
      env.instructionIndex += 1
    }
  }

  object Minus extends Instruction {
    override protected def updateEnvironment(env: Environment) {
      val value = env.memory.getOrElse(env.memoryIndex, 0)
      env.memory.put(env.memoryIndex,
        if (value == 0) 255
        else value - 1
      )
      env.instructionIndex += 1
    }
  }

  object Dot extends Instruction {
    override protected def updateEnvironment(env: Environment) {
      val value = env.memory.getOrElse(env.memoryIndex, 0)
      writer.print(value.toChar)
      env.instructionIndex += 1
    }
  }

  object Comma extends Instruction {
    override protected def updateEnvironment(env: Environment) {
      val input = env.input(env.inputIndex).toInt
      env.inputIndex += 1
      env.memory.put(env.memoryIndex, input)
      env.instructionIndex += 1
    }
  }

  class LeftBracket(val index: Int) extends Instruction {
    var matchingRightBracket: RightBracket = null

    override protected def updateEnvironment(env: Environment) {
      val value = env.memory.getOrElse(env.memoryIndex, 0)
      if (value == 0) env.instructionIndex = matchingRightBracket.index
      else env.instructionIndex += 1
    }
  }

  class RightBracket(val index: Int, val matchingLeftBracket: LeftBracket) extends Instruction {
    override protected def updateEnvironment(env: Environment) {
      val value = env.memory.getOrElse(env.memoryIndex, 0)
      if (value != 0) env.instructionIndex = matchingLeftBracket.index
      else env.instructionIndex += 1
    }
  }

  def main(a: Array[String]) {
    val reader = new BufferedReader(
      if (a.length == 0) new InputStreamReader(System.in)
      else new FileReader(a(0))
    )

    val Array(n, m) = reader.readLine().split(' ').map(_.toInt)
    val env = Environment(reader.readLine().take(n))
    val brackets = new mutable.ArrayStack[LeftBracket]

    for (i <- 0 until m) {
      val line = reader.readLine()
      for (c <- line) {
        c match {
          case '>' => env.instructions += RightArrow
          case '<' => env.instructions += LeftArrow
          case '+' => env.instructions += Plus
          case '-' => env.instructions += Minus
          case '.' => env.instructions += Dot
          case ',' => env.instructions += Comma
          case '[' => {
            val leftBracket = new LeftBracket(env.instructions.size)
            env.instructions += leftBracket
            brackets.push(leftBracket)
          }
          case ']' => {
            val leftBracket = brackets.pop()
            val rightBracket = new RightBracket(env.instructions.size, leftBracket)
            leftBracket.matchingRightBracket = rightBracket
            env.instructions += rightBracket
          }
          case _ =>
        }
      }
    }

    reader.close()

    var instruction = env.instructions(0)
    while (instruction != null) {
      instruction = instruction.execute(env)
    }

    writer.flush()
    writer.close()
  }
}
