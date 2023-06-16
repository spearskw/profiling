class CostMatrix {

    private val costMap: Map<Pair<Int, Int>, Int>
    init {
        val lines = {}.javaClass.classLoader
            .getResourceAsStream("edges.csv")
            .bufferedReader().readLines()

        costMap = lines
            .drop(1)
            .map { parseLine(it) }
            .associate { Pair(it[0], it[1]) to it[2] }
    }

    fun findCost(origin: Node, destination: Node): Int {
        val pair = Pair(origin.id, destination.id)
        return costMap[pair]!!
    }

    private fun parseLine(line: String): List<Int> {
        return line.split(",").map { it.trim().toInt() }
    }
}