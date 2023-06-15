fun main() {
    writeCsv(1000)
    computeStuff(CsvReader.readCsv())
}

fun computeStuff(lines: List<String>) {
    println(lines.size)
}