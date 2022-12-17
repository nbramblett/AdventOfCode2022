package day11

class Monkey {
    val items = mutableListOf<Long>()
    lateinit var test : (Long) -> Boolean
    lateinit var op : (Long) -> Long
    var fal = -1
    var tru = -1
    var inspections = 0
    var div = 1

    constructor(vararg startingItems : Long){
        items.addAll(startingItems.toTypedArray())
    }

    fun step(oth : List<Monkey>) {
        val k = items.size
        repeat(k) {
            var item = items.removeFirst()
            item = op(item)/3
            val index = if (test(item)) tru else fal
            oth[index].items.add(item)
            inspections++
        }
    }

    fun step2(oth : List<Monkey>, multiple : Int) {
        val k = items.size
        repeat(k) {
            var item = items.removeFirst()
            item = op(item)
            val index = if (test(item)) tru else fal
            item %= multiple
            oth[index].items.add(item)
            inspections++
        }
    }
}


fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val monlines = lines.chunked(7)
    val monkeys = monlines.toList().map { parseMonkey(it) }
    repeat(20) {
        monkeys.forEach { it.step(monkeys) }
        monkeys.forEach { print("${it.inspections}, ") }
        println("")
    }
    monkeys.forEach{println("${it.tru} ${it.fal}")}
    val results = monkeys.map { it.inspections }.sorted()
    results.forEach{println(it)}
    println()
    println(results.last().toLong()*results[results.lastIndex-1].toLong())
}

fun part2(lines : List<String>) {
    val monlines = lines.chunked(7)
    val monkeys = monlines.toList().map { parseMonkey(it) }
    val div = monkeys.map { it.div }.reduce { acc, i ->  acc*i}
    repeat(10000) {
        monkeys.forEach { it.step2(monkeys, div) }
    }
    val results = monkeys.map { it.inspections }.sorted()
    results.forEach{println(it)}
    println()
    println(results.last().toLong()*results[results.lastIndex-1].toLong())
}

fun parseMonkey(lines : List<String>) : Monkey {
    val mon = Monkey(*(lines[1].trim().removePrefix("Starting items: ").split(", ").map { it.toLong() }.toLongArray()))
    mon.op = parseOp(lines[2])
    mon.div = lines[3].trim().removePrefix("Test: divisible by ").toInt()
    mon.test = {i -> (i % mon.div).toInt() == 0 }
    mon.tru = lines[4].trim().removePrefix("If true: throw to monkey ").toInt()
    mon.fal = lines[5].trim().removePrefix("If false: throw to monkey ").toInt()
    return mon
}

fun parseOp(line :String) : (Long) -> Long {
    val st = line.trim().removePrefix("Operation: new = ")
    val sts = st.split(" ")
    return {old ->
        val x = if(sts[2] == "old") old else sts[2].toLong()
        if(sts[1][0] == '+') old + x
        else old * x
    }
}