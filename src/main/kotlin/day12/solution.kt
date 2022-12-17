package day12

fun solve(vararg lines : List<String>) {
    part1(lines[0])
    part2(lines[0])
}

fun part1(lines : List<String>) {
    val grid = lines.map { it.toCharArray().toMutableList() }
    val start = Pair(lines.indexOfFirst { it.contains("S") }, lines.first { it.contains("S") }.indexOf("S"))
    val end =  Pair(lines.indexOfFirst { it.contains("E") }, lines.first { it.contains("E") }.indexOf("E"))
    grid[start.first][start.second] = 'a'
    grid[end.first][end.second] = 'z'
    val dists = bfs(grid, start)
    println(dists[end])
}

fun part2(lines : List<String>) {
    val grid = lines.map { it.toCharArray().toMutableList()}
    val starts = mutableListOf<Pair<Int, Int>>()
    for (i in 0..grid.lastIndex) {
        for (j in 0..grid[i].lastIndex) {
            if(grid[i][j] == 'a' || grid[i][j] == 'S') {
                starts.add(Pair(i, j))
            }
        }
    }

    val end =  Pair(lines.indexOfFirst { it.contains("E") }, lines.first { it.contains("E") }.indexOf("E"))
    grid[end.first][end.second] = 'z'
    var min = Integer.MAX_VALUE
    starts.forEach { start ->
        grid[start.first][start.second] = 'a'
        val dists = bfs(grid, start)
        val dist = dists[end]
        if(dist != null && dist < min) min = dist
    }
    println(min)
}

fun bfs(grid : List<List<Char>>, start : Pair<Int, Int>) : Map<Pair<Int, Int>, Int> {
    val queue = mutableListOf(start)
    val dist = mutableMapOf<Pair<Int, Int>, Int>()
    dist[start] = 0
    while (queue.isNotEmpty()) {
        val next = queue.removeFirst()
        val d = dist[next]
        if (d != null) {
            if(next.first-1 >= 0 && grid[next.first-1][next.second] <= grid[next.first][next.second] +1) {
                val pair = Pair(next.first-1, next.second)
                if (dist[pair] == null || dist[pair]!! > d+1) {
                    dist[pair] = d + 1
                    queue.add(pair)
                }
            }
            if(next.first+1 < grid.size && grid[next.first+1][next.second] <= grid[next.first][next.second] +1) {
                val pair = Pair(next.first+1, next.second)
                if (dist[pair] == null || dist[pair]!! > d+1) {
                    dist[pair] = d + 1
                    queue.add(pair)
                }
            }
            if(next.second-1 >= 0 && grid[next.first][next.second-1] <= grid[next.first][next.second] + 1) {
                val pair = Pair(next.first, next.second-1)
                if (dist[pair] == null || dist[pair]!! > d+1) {
                    dist[pair] = d + 1
                    queue.add(pair)
                }
            }
            if(next.second+1 < grid[0].size && grid[next.first][next.second+1] <= grid[next.first][next.second] +1) {
                val pair = Pair(next.first, next.second+1)
                if (dist[pair] == null || dist[pair]!! > d+1) {
                    dist[pair] = d + 1
                    queue.add(pair)
                }
            }
        }
    }
    return dist
}