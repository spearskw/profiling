import kotlin.io.path.toPath
import kotlin.random.Random
import kotlin.random.nextInt

fun writeCsv(numNodes: Int) {
    val file = {}.javaClass.classLoader
        .getResource("edges.csv")
        .toURI().toPath().toFile()

    val writer = file.bufferedWriter()
    writer.write("origin, destination, cost")
    writer.newLine()

    val range = (0 until numNodes)
    range.forEach { origin ->
        range.forEach { destination ->
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