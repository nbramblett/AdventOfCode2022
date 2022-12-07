package day7

typealias Command = List<String>

class Dir(function: () -> Unit) {
    val files : MutableList<Int> = mutableListOf()
    val children : MutableList<Dir> = mutableListOf()
    var parent : Dir? = null
    var name: String = ""

    fun size() : Int {
        var sum = 0
        files.forEach { sum += it }
        children.forEach { sum += it.size() }
        return sum
    }


    fun doSizes(limit: Int, doer : (Int) -> Unit) : Int {
        var sum = 0
        files.forEach { sum += it }
        children.forEach { sum += it.doSizes(limit, doer) }
        if (sum <= limit) doer(sum)
        return sum
    }

    fun print(prefix : String) {
        println(prefix + name)
        children.forEach { it.print("$prefix ") }
        files.forEach { println("$prefix $it") }
    }
}

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    var cmds = breakIntoCommands(lines)
    println(cmds)
    var dir : Dir? = null
    cmds.forEach {
        dir = parseCmd(it, dir)
    }
    while (dir?.parent != null) {
        dir = dir?.parent
    }
    //dir?.print("")
    var sum = 0
    dir?.doSizes(100000) { v -> sum += v }
    println(sum)
}

fun part2(lines : List<String>) {
    var cmds = breakIntoCommands(lines)
    println(cmds)
    var dir : Dir? = null
    cmds.forEach {
        dir = parseCmd(it, dir)
    }
    while (dir?.parent != null) {
        dir = dir?.parent
    }
    //dir?.print("")
    val sizes = mutableListOf<Int>()
    dir?.doSizes(100000000) { v -> sizes.add(v) }
    sizes.sort()
    val remainingSize = 70000000 - dir?.size()!!
    sizes.forEach {
        if(it+remainingSize >= 30000000) {
            println(it)
            return
        }
    }

}

fun parseCmd(cmd : Command, dir : Dir?) : Dir? {
    var first = cmd[0].removePrefix("$ ")
    if (first.startsWith("cd")) {
        var file = first.removePrefix("cd ")
        if (file == "..") {
            return dir?.parent
        }
        var d = Dir{}
        d.name = file
        if (dir == null) {
            return d
        }
        dir.children.add(d)
        d.parent = dir
        return d
    } else { // ls
        if (dir == null) {
            return null
        }
        var rest = cmd.subList(1, cmd.size)
        rest.filter { r -> !r.startsWith("dir") }.map{ r -> r.split(" ")[0].toInt() }.forEach { dir.files.add(it) }
        return dir
    }

}

fun breakIntoCommands(lines : List<String>) : List<Command>{
    val commands = mutableListOf<Command>()
    var command = mutableListOf<String>()
    lines.forEach {
        if (it.startsWith("$")) {
            if(command.isNotEmpty()) commands.add(command)
            command = mutableListOf<String>(it)
        } else {
            command.add(it)
        }
    }
    commands.add(command)
    return commands
}