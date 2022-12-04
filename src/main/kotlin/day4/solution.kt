package day4

typealias Cells = Pair<Pair<Int, Int>, Pair<Int, Int>>

fun solve(vararg lines : List<String>) {
    part1(lines[0].map{parsePair(it)})
    part2(lines[0].map{parsePair(it)})
}

fun part1(cells : List<Cells>) {
    var count = 0
    cells.forEach{
        if((it.first.first <= it.second.first && it.first.second >= it.second.second)
            || (it.second.first <= it.first.first && it.second.second >= it.first.second))
        {
            count++
        }
    }
    println(count)
}

fun part2(cells : List<Cells>) {
    var count = 0
    cells.forEach{
        if((it.first.second >= it.second.first && it.first.first <= it.second.second))
        {
            count++
        }
    }
    println(count)
}

fun parsePair(line : String) : Cells {
    val pairs = line.split(",")
    val pair1 = pairs[0].split("-").map{it.toInt()}
    val pair2 = pairs[1].split("-").map{it.toInt()}
    return Pair(Pair(pair1[0], pair1[1]), Pair(pair2[0], pair2[1]))
}