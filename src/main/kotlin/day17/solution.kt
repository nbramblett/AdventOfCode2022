package day17

import kotlin.math.max

typealias Coord = Pair<Int, Int>

typealias Shape = Array<Coord>

val shapes = listOf<Shape>(
    arrayOf(Coord(0, 0), Coord(0, 1), Coord(0, 2), Coord(0, 3)),
    arrayOf(Coord(0, 1), Coord(1, 1), Coord(1, 2), Coord(2, 1), Coord(1, 0)),
    arrayOf(Coord(0, 0), Coord(0, 1), Coord(0, 2), Coord(1, 2), Coord(2, 2)),
    arrayOf(Coord(0, 0), Coord(1, 0), Coord(2, 0), Coord(3, 0)),
    arrayOf(Coord(0, 0), Coord(0, 1), Coord(1, 1), Coord(1, 0))
)

class Rock {
    lateinit var shape: Shape
    lateinit var coord: Coord

    // Return true when at rest
    fun fall(step: Char, blocked: Set<Coord>): Boolean {
        var potentialCoord: Coord
        if (step == '>') {
            potentialCoord = Coord(coord.first, coord.second + 1)
        } else {
            potentialCoord = Coord(coord.first, coord.second - 1)
        }
        for (sc in shape) {
            val nc = Coord(sc.first + potentialCoord.first, sc.second + potentialCoord.second)
            if (nc.second > 6 || nc.second < 0 || blocked.contains(nc)) {
                potentialCoord = coord
                break
            }
        }
        coord = potentialCoord
        if (coord.first == 0) {
            return true
        }
        potentialCoord = Coord(potentialCoord.first - 1, potentialCoord.second)
        for (sc in shape) {
            val nc = Coord(sc.first + potentialCoord.first, sc.second + potentialCoord.second)
            if (blocked.contains(nc)) {
                return true
            }
        }
        coord = potentialCoord
        return false
    }
}

class Cave {
    var covered = mutableSetOf<Coord>()
    var top = 0

    fun drop(steps: List<Char>) {
        var rockInd = 0
        var stepInd = 0
        repeat(2022) {
            val rock = Rock()
            rock.shape = shapes[rockInd]
            rock.coord = Coord(top + 3, 2)
            var resting = false
            while (!resting) {
                resting = rock.fall(steps[stepInd], covered)
                stepInd++
                stepInd %= steps.size
                //this.print(rock)
            }
            rock.shape.forEach {
                covered.add(
                    Coord(
                        rock.coord.first + it.first,
                        rock.coord.second + it.second
                    )
                )
            }
            top = covered.map { it.first }.reduce { acc, pair -> max(acc, pair) } + 1
            rockInd++
            rockInd %= shapes.size
        }
        println(top)
    }


    private fun print(rock: Rock?) {
        for (i in 6 downTo 0) {
            print("|")
            for (j in 0..6) {
                if (covered.contains(
                        Coord(
                            i,
                            j
                        )
                    ) || rock != null && rock.shape.map {
                        Coord(
                            it.first + rock.coord.first,
                            it.second + rock.coord.second
                        )
                    }.contains(
                        Coord(i, j)
                    )
                ) {
                    print("@")
                } else print(" ")
            }
            print("|")
            println()
        }
        println("---------")
    }
}

fun solve(vararg lines: List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines: List<String>) {
    val cave = Cave()
    cave.drop(lines[0].toList())
}

fun part2(lines: List<String>) {
}