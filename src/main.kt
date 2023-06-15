fun main() {
    val lines = CsvReader.readCsv()
    computeStuff(lines)
}

fun computeStuff(lines: List<String>) {
    println(lines.size)
}