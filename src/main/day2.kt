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

    val bow = dim.map {
        val sepString = it.split("x")
        val sep = sepString.map { it.toInt() }
        sep.reduce { acc, i -> acc * i  }
    }

    val ribbon = dim.map {
        val sepString = it.split("x")
        val sep = sepString.map { it.toInt() }
        val min = sep.min()!!
        val list = sep.toMutableList()
        var iter = 0
        for(i in sep) {
            if (i == min) {
                list.removeAt(iter)
                break
            } else iter += 1
        }
        val nextSmallest = list.min()!!
        (min * 2)  + (nextSmallest * 2)
    }
    val total = bow.mapIndexed { indx, i ->
        i + ribbon[indx]
    }

    //part1
    println("The total square feet that the elves need is ${ans.sum()}")
    //part2
    println("The total amount of ribbon they will need is ${total.sum()}")
}