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
        .getResource("edges.csv")
        .toURI().toPath().toFile()

    val writer = file.bufferedWriter()
    writer.write("origin, destination, cost")
    writer.newLine()

    val nodeIds = (0 until numNodes)
    nodeIds.forEach { origin ->
        nodeIds.forEach { destination ->
            if (origin == destination) {
                writer.write("${origin}, ${destination}, 0")
            } else {
                writer.write("${origin}, ${destination}, ${Random.nextInt((10..100))}")
            }
            writer.newLine()
        }
    }

    writer.close()
}