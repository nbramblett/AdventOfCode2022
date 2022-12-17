package day15

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

typealias Coord = Pair<Int, Int>



fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val y = 2000000
    val covered = mutableSetOf<Coord>()
    val pairs = lines.map { parseLine(it) }
    pairs.forEach {
        val dist = abs(it.first.first - it.second.first) + abs(it.first.second - it.second.second)
        val coverDist = abs(it.first.second - y)
        if (coverDist <= dist) {
            val diff = dist-coverDist
            for(i in it.first.first-diff .. it.first.first+diff){
                if (Coord(i, y) == it.second) continue
                covered.add(Coord(i, y))
            }
        }
    }
    println(covered.size)
//    pairs.forEach {
//        val dist = abs(it.first.first - it.second.first) + abs(it.first.second - it.second.second)
//        println(dist)
//        for (i in -dist..dist) {
//            println(i)
//            for (j in -dist-i..dist+i) {
//                if (abs(i)+abs(j) > dist) continue
//                if (Coord(it.first.first+i, it.first.second+j) == it.second) continue
//                covered.add(Coord(it.first.first+i, it.first.second+j))
//            }
//        }
//        println(covered.size)
//    }
}

fun part2(lines : List<String>) {
    val why = 4000000
    val pairs = lines.map { parseLine(it) }
    repeat(why) { y ->
        val coveredRanges = mutableListOf<Coord>()
        pairs.forEach {
            val dist =
                abs(it.first.first - it.second.first) + abs(it.first.second - it.second.second)
            val coverDist = abs(it.first.second - y)
            if (coverDist <= dist) {
                val diff = dist - coverDist
                coveredRanges.add(max(it.first.first - diff, 0) to min(it.first.first + diff, why))
            }
        }
        // pretty sure this doesn't cover every case but it thankfully covers mine
        var coveredRange = Coord(why/2,why/2)
        coveredRanges.sortBy { it.first }
        coveredRanges.forEach {
            if(it.first < coveredRange.first && it.second >= coveredRange.first){
                coveredRange = Coord(it.first, coveredRange.second)
            }
            if(it.second > coveredRange.second && it.first <= coveredRange.second){
                coveredRange = Coord(coveredRange.first, it.second)
            }
        }
        coveredRanges.reverse()
        coveredRanges.forEach {
            if(it.first < coveredRange.first && it.second >= coveredRange.first){
                coveredRange = Coord(it.first, coveredRange.second)
            }
            if(it.second > coveredRange.second && it.first <= coveredRange.second){
                coveredRange = Coord(coveredRange.first, it.second)
            }
        }
        if(coveredRange != Coord(0, why)) {
            println("$y $coveredRange")
            println((coveredRange.second.toLong()+1)*4000000 + y)
        }
    }
}

val regeX = Regex("x=-?\\d+")
val regeY = Regex("y=-?\\d+")
fun parseLine(line : String) : Pair<Coord, Coord> {
    return line.split(":")
        .map { Pair(regeX.find(it)!!, regeY.find(it)!!) }
        .map { Pair(it.first.value.removePrefix("x=").toInt(), it.second.value.removePrefix("y=").toInt()) }
        .let{it[0] to it[1]}
}