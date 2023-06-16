fun main() {
    val problem = createRandomProblem(100)
    val bestTour = findBestTour(problem, 1000)
    val bestTourCost = calcCost(problem.costMatrix, bestTour)
    println("Cost of the best tour is ${bestTourCost}")
}

fun findBestTour(problem: TspProblem, numIterations: Int): List<Node> {
    return (0 until numIterations)
        .map { problem.nodes.shuffled() }
        .minBy { calcCost(problem.costMatrix, it) }
}

fun calcCost(costMatrix: CostMatrix, tour: List<Node>): Int {
    return tour.zipWithNext { origin, destination ->
        costMatrix.findCost(origin, destination)
    }.sum() + costMatrix.findCost(tour.last(), tour.first())
}