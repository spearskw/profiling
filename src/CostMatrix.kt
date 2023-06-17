class CostMatrix {

    private val costArray: MutableList<MutableList<Int>> = mutableListOf()
    init {
        val lines = {}.javaClass.classLoader
            .getResourceAsStream("edges.csv")!!
            .bufferedReader().readLines()

        lines.drop(1).forEach {
            val (rowIndex, colIndex, cost) = parseLine(it)
            if (rowIndex >= costArray.size) {
                costArray.add(rowIndex, mutableListOf())
            }
            costArray[rowIndex].add(colIndex, cost)
        }
    }

    fun findCost(origin: Node, destination: Node): Int {
        return costArray[origin.id][destination.id]
    }

    private fun parseLine(line: String): List<Int> {
        return line.split(",").map { it.trim().toInt() }
    }
}