package day10

class Adder {
    var x = 1
    var cycle = 0
    val cycleX = mutableListOf<Int>()
    fun step() {
        cycle++
        if (cycle % 40 == 20) {
            cycleX.add(x*cycle)
        }
    }

    fun step2() {
        cycle++
        cycleX.add(x)
    }

    fun add(v : Int) {
        step()
        step()
        x += v
    }

    fun add2(v: Int) {
        step2()
        step2()
        x += v
    }
}

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val adder = Adder()
    lines.forEach {
        val inst = it.split(" ")
        if(inst[0] == "noop") adder.step()
        else adder.add(inst[1].toInt())
    }
    println(adder.cycleX.fold(0){ i, v -> i+v})
}

fun part2(lines : List<String>) {
    val adder = Adder()
    lines.forEach {
        val inst = it.split(" ")
        if(inst[0] == "noop") adder.step2()
        else adder.add2(inst[1].toInt())
    }
    adder.cycleX.forEachIndexed{i, it ->
        val pos = i % 40
        if(it - 1 == pos || it == pos || it + 1 == pos){
            print('@')
        } else print(' ')
        if(i %40 == 39 ) {
            println()
        }
    }
}
