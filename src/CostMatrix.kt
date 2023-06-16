class CostMatrix {

    fun findCost(origin: Node, destination: Node): Int {
        val lines = {}.javaClass.classLoader
            .getResourceAsStream("edges.csv")
            .bufferedReader().readLines()

        val lineWithCost = lines
            .drop(1)
            .map { parseLine(it) }
            .find { it[0] == origin.id && it[1] == destination.id }

        return lineWithCost!![2]
    }

    private fun parseLine(line: String): List<Int> {
        return line.split(",").map { it.trim().toInt() }
    }
}