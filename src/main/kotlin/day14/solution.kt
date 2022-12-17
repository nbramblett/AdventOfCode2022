package day14

import kotlin.math.min
import kotlin.math.max


typealias Coord = Pair<Int, Int>

class Sand {
    var coord = Coord(500, 0)

    fun descend(blocked : Set<Coord>) : Coord? {
        var newCoord = Coord(coord.first, coord.second+1)
        if(!blocked.contains(newCoord)) {
            return newCoord
        }
        newCoord = Coord(coord.first-1, coord.second+1)
        if(!blocked.contains(newCoord)) {
            return newCoord
        }
        newCoord = Coord(coord.first+1, coord.second+1)
        if(!blocked.contains(newCoord)) {
            return newCoord
        }

        return null
    }
}

class Cave {
    var lowestY = -1
    val blocked = mutableSetOf<Coord>()
    var restCount = 0

    fun dropSand(part2 : Boolean) {
        OUTER@while(!blocked.contains(Coord(500,0))) {
            val sand = Sand()
            var next: Coord? = sand.coord
            while (next != null) {
                next = sand.descend(blocked)
                if (next == null) {
                    restCount++
                    blocked.add(sand.coord)
                    break
                }
                if (next.second == lowestY) {
                    if (!part2) break@OUTER
                    blocked.add(next)
                    break
                }
                sand.coord = next
            }
        }
    }
}

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val cave = Cave()
    lines.forEach { cave.blocked.addAll(getBlocked(it)) }
    cave.lowestY = cave.blocked.map{it.second}.reduce { acc, pair -> max(acc, pair) }
    println(cave.lowestY)
    cave.dropSand(false)
    println(cave.restCount)
}

fun part2(lines : List<String>) {
    val cave = Cave()
    lines.forEach { cave.blocked.addAll(getBlocked(it)) }
    cave.lowestY = cave.blocked.map{it.second}.reduce { acc, pair -> max(acc, pair) } + 2
    println(cave.lowestY)
    cave.dropSand(true)
    println(cave.restCount)
}

fun getBlocked(line : String) : Set<Coord> {
    val blocked = mutableSetOf<Coord>()
    val coords = line.split(" -> ").map { it.split(",") }.map { Coord(it[0].toInt(), it[1].toInt()) }
    for (i in 1..coords.lastIndex) {
        val last = coords[i-1]
        val next = coords[i]
        if (next.first == last.first) {
            for(j in min(next.second, last.second)..max(next.second, last.second)) {
                blocked.add(Coord(next.first, j))
            }
        } else {
            for(j in min(next.first, last.first)..max(next.first, last.first)) {
                blocked.add(Coord(j, next.second))
            }
        }
    }
    return blocked
}