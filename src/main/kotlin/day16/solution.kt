package day16

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Valve(val name : String, val rate : Int, val leads : List<String>)

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val cave = mutableMapOf<String, Valve>()
    lines.forEach {
        val v = getValve(it)
        cave[v.name] = v
    }
    println(cave)
    println()
}

fun part2(lines : List<String>) {
}

fun getValve(line : String) : Valve {
    val regex = Regex("Valve ([A-Z]+) has flow rate=(\\d+); tunnel(s?) lead(s?) to valve(s?) (.*)")
    val groups = regex.find(line)?.groupValues!!
    return Valve(groups[1], groups[2].toInt(), groups.last().split(", "))
}

data class Node(val links : Map<String, Int>, val name: String)

fun reduce(cave : Map<String, Valve>) : Node {
 return Node(mapOf(), "")
}

fun relevant(cave : Map<String, Valve>, name : String) : Boolean {
    return name == "AA" || cave[name]!!.rate > 0
}