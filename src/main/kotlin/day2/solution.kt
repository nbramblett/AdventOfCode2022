package day2

fun solve(vararg lines : List<String>) {
    val ints = mutableListOf<Int>();
    part1(ints)
    part2(ints)
}

fun part1(elves : List<Int>) {
    println(elves.maxOrNull())
}

fun part2(elves : MutableList<Int>) {
    elves.sort()
    elves.reverse()
    println(elves.get(0) + elves.get(1) + elves.get(2))
}