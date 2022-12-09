package day9

import kotlin.math.abs

class Knot {
    var x : Int = 0
    var y : Int = 0
    val visited = mutableSetOf<Pair<Int, Int>>()

    fun move(dir: Char) {
        when(dir){
            'U' -> y++
            'D' -> y--
            'L' -> x--
            'R' -> x++
        }
    }

    fun follow(oth : Knot) {
        visited.add(Pair(x, y))
        if(abs(oth.x-x) >1 || abs(oth.y-y) > 1) {
            x += oth.x.compareTo(x)
            y += oth.y.compareTo(y)
        }
        visited.add(Pair(x, y)) // catch off-by-one errors
    }
}


fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val h = Knot()
    val t = Knot()
    lines.forEach{
        val inst = it.split(" ")
        repeat(inst[1].toInt()){
            h.move(inst[0][0])
            t.follow(h)
        }
    }
    println(t.visited.size)
}

fun part2(lines : List<String>) {
    val knots = arrayOf(Knot(), Knot(),Knot(),Knot(),Knot(),Knot(),Knot(),Knot(),Knot(),Knot(),Knot())
    lines.forEach{
        val inst = it.split(" ")
        repeat(inst[1].toInt()){
            knots[0].move(inst[0][0])
            for(i in 1..knots.lastIndex) {
                knots[i].follow(knots[i-1])
            }
        }
    }
    println(knots[9].visited.size)
}
