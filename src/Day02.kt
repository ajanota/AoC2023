fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        val max = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        input.forEach{ line ->
            var isGamePossible = true
            val gameNumber = getGameNumber(line)
            val grabs = getGrabs(line)

            grabs.iterator().forEach {
                val ( number, color) = it.destructured
                if (number.toInt() > max[color]!!) {
                    if (isGamePossible) isGamePossible = false
                }
            }

            if (isGamePossible) {
                sum += gameNumber.toInt()
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val max = mutableMapOf(
            "blue" to 0,
            "red" to 0,
            "green" to 0
        )

        input.forEach{ line ->
            val grabs = getGrabs(line)

            grabs.iterator().forEach {
                val ( number, color) = it.destructured
                if (number.toInt() > max[color]!!) {
                    max[color] = number.toInt()
                }
            }

            sum += max["blue"]!! * max["red"]!! * max["green"]!!
            max["blue"] = 0
            max["red"] = 0
            max["green"] = 0
        }

        return sum
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun getGameNumber(line: String) = line.split(":")[0].split(" ")[1]
fun getGrabs(line: String): Sequence<MatchResult> {
    val regexp = """(\d+) (\w+)""".toRegex()
    return regexp.findAll(line.split(":")[1])
}
