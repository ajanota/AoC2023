fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach{ line ->
            sum += "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val word2number = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )

        var sum = 0
        var indexOfFirst = 10000
        var indexOfLast = 0
        var first = ""
        var last = ""

        fun findFirstAndLastFor(line: String, list: List<String>) {
            list.forEach { numberAsWord ->
                if (line.indexOf(numberAsWord) > -1 && line.indexOf(numberAsWord) < indexOfFirst) {
                    indexOfFirst = line.indexOf(numberAsWord)
                    first = numberAsWord
                }
                if (line.lastIndexOf(numberAsWord) > -1 &&  line.lastIndexOf(numberAsWord) > indexOfLast) {
                    indexOfLast = line.lastIndexOf(numberAsWord)
                    last = numberAsWord
                }
            }
        }

        input.forEach{ line ->
            findFirstAndLastFor(line, word2number.keys.toList())
            findFirstAndLastFor(line, word2number.values.toList())
            first = if (word2number.keys.contains(first)) word2number.get(first)!! else first
            last = if (word2number.keys.contains(last)) word2number.get(last)!! else last
            if (last.isBlank()) last = first

            sum += "$first$last".toInt()

            indexOfFirst = 10000
            indexOfLast = 0
            first = ""
            last = ""
        }
        return sum
    }


    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
