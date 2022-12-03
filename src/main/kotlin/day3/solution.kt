package day3

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    var sum = 0
    lines.forEach{
        val first = it.substring(0, it.length/2)
        val sec = it.substring(it.length/2, it.length)

        var common = ' '
        for(i in first) {
            if(sec.contains(i)) {
                common = i
                break
            }
        }

        var v = 0
        v = if (common.isLowerCase()) {
            common.code - 96
        } else {
            common.code - 38
        }
        sum += v
    }
    println(sum)
}

fun part2(lines : List<String>) {
    val groups = lines.chunked(3)
    var sum = 0
    groups.forEach{
        var common = ' '
        val commons = mutableSetOf<Char>()
        it[0].forEach{
            commons.add(it)
        }
        val shared = mutableSetOf<Char>()
        it[1].forEach {
            if(commons.contains(it)) {
                shared.add(it)
            }
        }
        it[2].forEach {
            if(shared.contains(it)) {
                common = it
            }
        }
        var v = 0
        v = if (common.isLowerCase()) {
            common.code - 96
        } else {
            common.code - 38
        }
        sum += v
    }
    println(sum)
}