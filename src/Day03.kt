import java.math.BigInteger

fun isSymbol(char: Char): Boolean {
    return !char.isDigit() && char != '.'
}

    fun checkIsPartInAdjacentLine(input: List<String>, lineNumber: Int, charPosition: Int): Boolean {
        val isSymbolBeforeCurrentPosition = if (charPosition > 0) {
            isSymbol(input[lineNumber][charPosition.dec()])
        } else false
        val isSymbolCurrentPosition = isSymbol(input[lineNumber][charPosition])
        val isSymbolAfterCurrentPosition = if (charPosition < input[lineNumber].length - 1) {
            isSymbol(input[lineNumber][charPosition.inc()])
        } else false

        return isSymbolBeforeCurrentPosition || isSymbolCurrentPosition || isSymbolAfterCurrentPosition
    }

fun checkIsPartCurrenLine(input: List<String>, currentLineNumber: Int, charPosition: Int): Boolean {
    val isSymbolAtBeforePosition = if (charPosition > 0) isSymbol(input[currentLineNumber][charPosition.dec()]) else false
    val isSymbolAsNextPosition = if (charPosition < input[currentLineNumber].length - 1) {
        isSymbol(input[currentLineNumber][charPosition.inc()])
    } else false

    return isSymbolAtBeforePosition || isSymbolAsNextPosition
}


fun checkIsPartFor(input: List<String>, currentLineNumber: Int, charPosition: Int): Boolean {
    val isPartInPrevLine = if (currentLineNumber > 0) {
        checkIsPartInAdjacentLine(input, currentLineNumber.dec(), charPosition)
    } else false
    val isPartInCurrentLine = checkIsPartCurrenLine(input, currentLineNumber, charPosition)
    val isPartInNextLine = if (currentLineNumber <= input.size - 2) {
        checkIsPartInAdjacentLine(input, currentLineNumber.inc(), charPosition)
    } else false

    return isPartInPrevLine || isPartInCurrentLine || isPartInNextLine
}



fun main() {

    fun part1(input: List<String>): BigInteger {
        var sum = BigInteger.ZERO
        var currentLineNumber = 0
        input.forEach { line ->
            var number = ""
            var insideNumber = false
            var isPart = false
            line.forEachIndexed{ charPosition, char ->
                if (char.isDigit()) {
                    insideNumber = true
                    number += char
                    if (!isPart) { isPart = checkIsPartFor(input, currentLineNumber, charPosition) }
                }
                if ((!char.isDigit() || charPosition == line.length - 1) && insideNumber) {
                    if (isPart) {
                        sum += number.toBigInteger()
                    }
                    number = ""
                    insideNumber = false
                    isPart = false
                }
            }

            currentLineNumber++
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}