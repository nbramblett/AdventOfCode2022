import day5.solve
import inputs.readProblem
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


@OptIn(ExperimentalTime::class)
fun main() {
    val lines = readProblem("5_1")

    val time = measureTime {  solve(lines) }
    println(time)
}