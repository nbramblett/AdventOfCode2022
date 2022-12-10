import day10.solve
import inputs.readProblem
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


@OptIn(ExperimentalTime::class)
fun main() {
    val lines = readProblem("10_1")

    val time = measureTime {  solve(lines) }
    println(time)
}