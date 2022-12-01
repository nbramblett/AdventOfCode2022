package inputs

import java.io.File

fun readProblem(name:String) : List<String> {
    return File("src/main/kotlin/inputs/${name}.txt").useLines { it.toList() }
}