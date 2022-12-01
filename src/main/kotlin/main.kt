import day1.solve
import inputs.readProblem
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


@OptIn(ExperimentalTime::class)
fun main() {
    val lines = readProblem("1_1")

    val time = measureTime {  solve(lines) }
    println(time)
}