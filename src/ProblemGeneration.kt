import kotlin.io.path.toPath
import kotlin.random.Random
import kotlin.random.nextInt

fun createRandomProblem(numNodes: Int): TspProblem {
    saveRandomProblem(numNodes)
    val costMatrix = CostMatrix()
    val nodes = (0 until numNodes).map { Node(it) }
    return TspProblem(costMatrix, nodes)
}

private fun saveRandomProblem(numNodes: Int) {
    val file = {}.javaClass.classLoader
        .getResource("edges.txt")
        .toURI().toPath().toFile()

    val writer = file.bufferedWriter()

    val nodeIds = (0 until numNodes)
    nodeIds.forEach { origin ->
        nodeIds.forEach { destination ->
            if (origin == destination) {
                writer.write("00")
            } else {
                writer.write("${Random.nextInt((10 until 100))}")
            }
        }
        writer.newLine()
    }

    writer.close()
}