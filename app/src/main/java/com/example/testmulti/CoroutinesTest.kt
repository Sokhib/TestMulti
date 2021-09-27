package com.example.testmulti

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

var acquired = 0

class Resource {
    init {
        acquired++
    } // Acquire the resource

    fun close() {
        acquired--
    } // Release the resource
}

//fun main() = runBlocking {
//    val channel = Channel<Int>()
//    val channel2 = Channel<Int>()
//    launch {
//        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
//        for (x in 1..5) channel.send(x * x)
//        delay(3000)
//        channel.send(12)
//    }
//    // here we print 6 received integers:
////    repeat(6) .{ println(channel.receive()) }
//    println(channel2.receive())
//    println("Done!")
//}

//Prime Numbers
//fun main() = runBlocking {
//    var cur = numbersFrom(2)
//    repeat(10) {
//        val i = cur.receive()
//        println(i)
//        cur = filter(cur, i)
//    }
//    coroutineContext.cancelChildren() // cancel all children to let main finish
//}
//
//fun CoroutineScope.numbersFrom(start: Int) = produce {
//    var x = start
//    while (true) send(x++) // infinite stream of integers from start
//}
//
//fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, i: Int) = produce {
//    for (x in numbers) if (x % i != 0) send(x)
//}


fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking {
    val time = System.currentTimeMillis()
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()} and time: ${System.currentTimeMillis() - time}")
}