fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach{ line ->
            sum += "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }
        return sum
    }

    val input = readInput("Day01")
    part1(input).println()
}
