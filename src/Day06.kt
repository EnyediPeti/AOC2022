fun main() {
    fun part1(input: List<String>): Int {
        val buffer = input[0]
        var counter = 4

        do {
            if (buffer.substring(counter - 4, counter).toSet().size != 4) {
                counter++
            }
        } while (buffer.substring(counter - 4, counter).toSet().size != 4)

        return counter
    }

    fun part2(input: List<String>): Int {
        val buffer = input[0]
        var counter = 14

        do {
            if (buffer.substring(counter - 14, counter).toSet().size != 14) {
                counter++
            }
        } while (buffer.substring(counter - 14, counter).toSet().size != 14)

        return counter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
