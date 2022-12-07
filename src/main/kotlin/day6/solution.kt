package day6

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val line = lines[0]
    println(unique(line, 4))
}

fun part2(lines : List<String>) {
    val line = lines[0]
    println(unique(line, 14))
}

fun unique(line : String, n : Int) : Int {
    line.forEachIndexed { index, _ ->
        if(index < n) {
            return@forEachIndexed
        }
        val sub = line.substring(index-n, index)
        if (sub.toCharArray().distinct().size == n) {
            return index
        }
    }
    return -1
}