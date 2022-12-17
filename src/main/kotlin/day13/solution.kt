package day13

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val pairs = lines.chunked(3).map { it.subList(0, 2) }
    var sum = 0
    pairs.forEachIndexed{ i, it ->
        val stat = compare(parseLine(it[0]).first, parseLine(it[1]).first)
        if(stat == -1) {
            sum += i+1
        }
    }
    println(sum)
}

fun part2(lines : List<String>) {
    val codes = mutableListOf("[[2]]", "[[6]]")
    lines.forEach { if (it.isNotEmpty()) codes.add(it) }
    val sorted = codes.map{ parseLine(it).first }.sortedWith{l1, l2 -> compare(l1, l2) }
    sorted.forEachIndexed{i, it ->
        val first = it[0]
        if(first is List<*> && first.size == 1) {
            println("$i $it")
        }
    }
}

fun parseLine(line : String) : Pair<List<Any>, Int> {
    val list = mutableListOf<Any>()
    var i = 0

    var digit = ""
    while(i <= line.lastIndex) {
        if (line[i] == '[') {
            val pair = parseLine(line.substring(i+1))
            list.add(pair.first)
            i += pair.second+1
        } else if (line[i] == ']') {
            if (digit.isNotEmpty()) {
                list.add(digit.toInt())
            }
            return Pair(list, i)
        } else if (line[i] == ',') {
            if(digit.isNotEmpty()) {
                list.add(digit.toInt())
            }
            digit = ""
        } else {
            digit += line[i]
        }
        i++
    }
    return Pair(list, line.lastIndex)
}

fun compare(l1 : List<Any>, l2 : List<Any>) : Int {
    l1.forEachIndexed { i, it ->
        if (i >= l2.size) {
            return 1
        }
        val v1 = it
        val v2 = l2[i]
        var comp = 0
        if(v1 is Int && v2 is Int) {
            comp = v1.compareTo(v2)
        } else if(v1 is List<*> && v2 is List<*>) {
            comp = compare(v1 as List<Any>, v2 as List<Any>)
        } else if (v1 is List<*>) {
            val v22 = listOf<Any>(v2 as Int)
            comp = compare(v1 as List<Any>, v22)
        } else {
            val v12 = listOf<Any>(v1 as Int)
            comp = compare(v12, v2 as List<Any>)
        }
        if (comp != 0) {
            return comp
        }
    }
    if (l1.size == l2.size) {
        return 0
    }
    return -1
}