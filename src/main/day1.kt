package main

import java.io.File

fun main(args: Array<String>) {
    fun readFileAsLinesUsingUseLines(fileName: String) = File(fileName).useLines { it.toList() }
    val inputDayOne = readFileAsLinesUsingUseLines("src/main/resources/inputday1.txt")

    val floors = inputDayOne[0].map {
        if(it == '(') {
            1
        }
        else -1
    }
    //part 1
    println("Santa needs to go to floor ${floors.sum()}")

    //part 2
    var ans = 0
    var x = 0
    for(i in floors) {
        if(ans < 0) {
            break
        } else if(i == 1) {
            ans += i
            x += 1
        } else {
            ans -= 1
            x += 1
        }
    }
    println(x)
}