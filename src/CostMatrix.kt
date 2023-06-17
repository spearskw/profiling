class CostMatrix {
    private val costArray = {}.javaClass.classLoader
        .getResourceAsStream("edges.txt")!!
        .bufferedReader()
        .useLines { lines ->
            lines.map { line ->
                line.chunked(2).map { it.toInt() }
            }.toList()
        }

    fun findCost(origin: Node, destination: Node): Int {
        return costArray[origin.id][destination.id]
    }
}