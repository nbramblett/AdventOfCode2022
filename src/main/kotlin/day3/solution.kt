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

        var common : Char = ' '
        OUTER@for(i in first.indices) {
            for(j in sec.indices) {
                if (first[i] == sec[j]) {
                    common = first[i]
                    break@OUTER
                }
            }
        }

        var v = 0
        if (common.lowercase()[0] == common) {
            v = common.code - 96
        } else {
            v = common.code - 38
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
        if (common.lowercase()[0] == common) {
            v = common.code - 96
        } else {
            v = common.code - 38
        }
        sum += v
    }
    println(sum)
}