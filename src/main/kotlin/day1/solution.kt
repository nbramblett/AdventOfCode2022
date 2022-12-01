package day1

fun solve(vararg lines : List<String>) {
    val elves = mutableListOf<Int>();
    var sum = 0
    for(line in lines[0]) {
        if (line.isEmpty()) {
            elves.add(sum)
            sum = 0
            continue
        }
        val cals = Integer.parseInt(line)
        sum += cals
    }
    part1(elves)
    part2(elves)
}

fun part1(elves : List<Int>) {
    println(elves.maxOrNull())
}

fun part2(elves : MutableList<Int>) {
    elves.sort()
    elves.reverse()
    println(elves.get(0) + elves.get(1) + elves.get(2))
}