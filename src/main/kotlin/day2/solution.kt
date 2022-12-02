package day2


val mov = mapOf<String, String>(
    "A" to "r",
    "B" to "p",
    "C" to "s",
    "X" to "r",
    "Y" to "p",
    "Z" to "s"
)

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(moves : List<String>) {
    var sum = 0
    moves.forEach{
        val mv = it.split(" ")
        val my = mov[mv[1]]
        val they = mov[mv[0]]
        sum += moveScore(my.orEmpty())
        sum += winScore(my.orEmpty(), they.orEmpty())
    }
    println(sum)
}

fun part2(moves : List<String>) {
    var sum = 0
    moves.forEach{
        val mv = it.split(" ")
        val they = mov[mv[0]].orEmpty()
        val stat = mv[1]
        val my = derive(they, stat)
        println(they + stat + my)
        sum += moveScore(my)
        sum += winScore(my, they)
    }
    println(sum)
}

fun moveScore(you :String) : Int {
    when (you) {
        "r" -> return 1
        "p" -> return 2
        "s" -> return 3
    }
    return 0
}

fun winScore(you : String, them : String) : Int {
    if (you == them) {
        return 3
    }
    if (you == "r") {
        if (them == "p") {
            return 0
        }
        return 6
    }
    if (you == "p") {
        if (them == "s") {
            return 0
        }
        return 6
    }
    if (them == "r") {
        return 0
    }
    return 6
}

fun derive(they : String , stat : String) : String {
    when (stat) {
        "Z" -> return if(they == "r") "p" else if (they == "p") "s" else "r"
        "Y" -> return they
        "X" -> return if(they == "r") "s" else if (they == "p") "r" else "p"
    }
    return ""
}