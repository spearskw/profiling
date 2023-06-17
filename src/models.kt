data class TspProblem(
    val costMatrix: CostMatrix,
    val nodes: List<Node>
)

data class Node(
    val id: Int
)

data class TourChange(
    val indexSwaps: List<Pair<Int, Int>>
) {
    fun reversed(): TourChange {
        return TourChange(this.indexSwaps.map { Pair(it.second, it.first) })
    }
}