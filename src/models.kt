data class TspProblem(
    val costMatrix: CostMatrix,
    val nodes: List<Node>
)

data class Node(
    val id: Int
)
