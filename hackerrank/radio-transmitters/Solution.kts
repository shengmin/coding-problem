import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// Complete the hackerlandRadioTransmitters function below.
fun hackerlandRadioTransmitters(rawHouses: Array<Int>, range: Int): Int {
  val houses = rawHouses.sortedArray()
  var count = 1
  // set the transimitter at house 0 for now
  var lastTransmitterLocation = houses[0];
  var minHouseLocation = houses[0];

  for (i in 1 until houses.size) {
    // If we move previous transmitter here, can we still cover all the houses
    // at the previous location
    val currentHouseLocation = houses[i]
    if (currentHouseLocation - range <= minHouseLocation) {
      // We can move the transmitter here and still cover all the houses
      lastTransmitterLocation = currentHouseLocation
    } else if (lastTransmitterLocation + range >= currentHouseLocation) {
      // The current house is already covered by the previous transmitter
      // No need to install new trasmitter
    } else {
      // We need to install a new transmitter here
      count++
      lastTransmitterLocation = currentHouseLocation
      minHouseLocation = currentHouseLocation
    }
  }

  return count
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val nk = scan.nextLine().split(" ")

    val n = nk[0].trim().toInt()

    val k = nk[1].trim().toInt()

    val x = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    val result = hackerlandRadioTransmitters(x, k)

    println(result)
}

main(arrayOf());
