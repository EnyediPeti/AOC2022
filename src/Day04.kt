fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { pairString ->
            val assignment = getAssignments(pairString)
            val first = assignment[0]
            val second = assignment[1]

            val firstRange = getRange(first)
            val secondRange = getRange(second)

            if (firstRange.contains(secondRange.first) && firstRange.contains(secondRange.last) ||
                secondRange.contains(firstRange.first) && secondRange.contains(firstRange.last)
            ) {
                1 as Int
            } else 0 as Int
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { pairString ->
            val assignment = getAssignments(pairString)
            val first = assignment[0]
            val second = assignment[1]

            val firstRange = getRange(first)
            val secondRange = getRange(second)

            if (firstRange.contains(secondRange.first) || firstRange.contains(secondRange.last) ||
                secondRange.contains(firstRange.first) || secondRange.contains(firstRange.last)
            ) {
                1 as Int
            } else 0 as Int
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun getAssignments(pairString: String): List<String> {
    return pairString.split(',')
}

fun getRange(rangeString: String): IntRange {
    val start = rangeString.takeWhile { it != '-' }
    val end = rangeString.takeLastWhile { it != '-' }

    return start.toInt()..end.toInt()
}