package main

import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {
    val reader = BufferedReader(FileReader("src/main/resources/inputday2.txt"))
    val dim = mutableListOf<String>()
    var x = 0
    var line = ""
    while(true) {
        val tmp = reader.readLine()
        if (tmp==null) {
            if(!line.isEmpty()) {
                dim.add(line)
                println(dim[x])
            }
            break
        } else {
            line += tmp
            dim.add(line)
            line = ""
            x += 1
        }
    }

    fun findDimensions(input: List<Int>): List<Int> {
        val a = input[0] * input[1] * 2
        val b = input [1] * input[2] * 2
        val c = input[2] * input[0] * 2
        return listOf(a, b, c)
    }

    fun findMinDimension(input: List<Int>): Int {
        val inter = input.map { it/2 }
        return inter.min()!!
    }

    val ans = dim.map {
        val sepString = it.split("x")
        val sep = sepString.map { it.toInt() }
        val area = findDimensions(sep)
        area.sum() + findMinDimension(area)
    }
    println("The total square feet that the elves need is ${ans.sum()}")
}