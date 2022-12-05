package day5

typealias Stack = ArrayDeque<Char>

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val splitdex = lines.indexOf("");
    val stacks = getStacks(lines.subList(0,splitdex))
    val inst = getInstructions(lines.subList(splitdex+1, lines.size))
    inst.forEach{
        repeat(it.first) {_ ->
            val moved = stacks[it.second].removeFirst()
            stacks[it.third].addFirst(moved)
        }
    }

    stacks.forEach{
        if(!it.isEmpty()){
            print(it.first())
        }
    }
    println()
}

fun part2(lines : List<String>) {
    val splitdex = lines.indexOf("");
    val stacks = getStacks(lines.subList(0,splitdex))
    val inst = getInstructions(lines.subList(splitdex+1, lines.size))
    inst.forEach{
        val moved = mutableListOf<Char>()
        repeat(it.first) {_ ->
            moved.add(stacks[it.second].removeFirst())
        }
        moved.reverse()
        moved.forEach { c -> stacks[it.third].addFirst(c) }
    }

    stacks.forEach{
        if(!it.isEmpty()){
            print(it.first())
        }
    }
    println()
}

fun getStacks(stackLines : List<String>) : List<Stack> {
    val indexToNum = mutableMapOf<Int, Int>()
    stackLines.last().forEachIndexed {i, it ->
        if(it != ' ') {
            indexToNum[i] = it.digitToInt()
        }
    }
    val stacks = mutableListOf<Stack>(ArrayDeque())
    indexToNum.keys.forEach{
        stacks.add(ArrayDeque())
    }
    stackLines.subList(0, stackLines.lastIndex).forEach{
        it.forEachIndexed {i, c ->
            if(c.isLetter()) {
                indexToNum[i]?.let { it1 -> stacks[it1].add(c) }
            }
        }
    }
    return stacks
}

fun getInstructions(lines : List<String>) : List<Triple<Int, Int, Int>> {
    val l = mutableListOf<Triple<Int, Int, Int>>()
    lines.forEach{ it ->
        val s = it.removePrefix("move ").split(" from ", " to ").map { it.toInt() }
        l.add(Triple(s[0], s[1], s[2]))
    }
    return l
}
