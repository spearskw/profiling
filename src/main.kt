import kotlin.random.Random

fun main() {
    val problem = createRandomProblem(1000)
    val bestTour = findBestTour(problem, 1000)
    val bestTourCost = calcCost(problem.costMatrix, bestTour)
    println("Cost of the best tour is ${bestTourCost}")
}

fun findBestTour(problem: TspProblem, numIterations: Int): List<Node> {
    val currentTour = problem.nodes.toMutableList()
    var currentCost = calcCost(problem.costMatrix, currentTour)

    for (i in 0 until numIterations) {
        val tourChange = generateTourChange(currentTour)
        val costDelta = calcCostDelta(currentTour, tourChange, problem.costMatrix)
        val newCost = currentCost + costDelta
        if (newCost < currentCost) {
            currentCost = newCost
            applyTourChange(currentTour, tourChange)
        }
    }

    return currentTour
}

fun generateTourChange(currentTour: List<Node>): TourChange {
    val index1 = Random.nextInt(currentTour.size)
    var index2 = Random.nextInt(currentTour.size)
    while (index2 == index1) {
        index2 = Random.nextInt(currentTour.size)
    }

    return TourChange(listOf(
        Pair(index1, index2),
        Pair(index2, index1)
    ))
}

fun calcCostDelta(currentTour: MutableList<Node>, tourChange: TourChange, costMatrix: CostMatrix): Int {
    val oldEdges = tourChange.indexSwaps.flatMap {
        findNeighbors(currentTour, it.first)
    }.toSet()
    applyTourChange(currentTour, tourChange)
    val newEdges = tourChange.indexSwaps.flatMap {
        findNeighbors(currentTour, it.first)
    }.toSet()
    applyTourChange(currentTour, tourChange.reversed())

    val newSum = newEdges.sumOf { costMatrix.findCost(it.first, it.second) }
    val oldSum = oldEdges.sumOf { costMatrix.findCost(it.first, it.second) }

    return newSum - oldSum
}

fun findNeighbors(currentTour: List<Node>, index: Int): List<Pair<Node, Node>> {
    if (index == 0) {
        return listOf(
            Pair(currentTour.last(), currentTour[0]),
            Pair(currentTour[0], currentTour[1])
        )
    }
    if (index == currentTour.size - 1) {
        return listOf(
            Pair(currentTour[index - 1], currentTour[index]),
            Pair(currentTour[index], currentTour[0])
        )
    }
    return listOf(
        Pair(currentTour[index - 1], currentTour[index]),
        Pair(currentTour[index], currentTour[index + 1])
    )
}

fun applyTourChange(currentTour: MutableList<Node>, tourChange: TourChange) {
    val newValues = tourChange.indexSwaps.map {
        currentTour[it.second]
    }

    tourChange.indexSwaps.forEachIndexed { i, swap ->
        currentTour[swap.first] = newValues[i]
    }
}


fun calcCost(costMatrix: CostMatrix, tour: List<Node>): Int {
    return tour.zipWithNext { origin, destination ->
        costMatrix.findCost(origin, destination)
    }.sum() + costMatrix.findCost(tour.last(), tour.first())
}