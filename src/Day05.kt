fun main() {

    class Move(val count: Int, val fromStack: Int, val toStack: Int)

    fun part1(input: List<String>): String {
        val blankLineIndex = input.indexOfFirst { it.isBlank() }
        val crates = input.subList(0, blankLineIndex - 1)
        val moves = input.subList(blankLineIndex + 1, input.size)

        val stackNo = input[blankLineIndex - 1].last { it.isDigit() }.digitToInt()

        val stacks = List(size = stackNo) { mutableListOf<Char>() }
        crates.asReversed().forEach { crateLevel ->
            crateLevel.chunked(4).forEachIndexed { index, content ->
                content[1].takeIf { it.isLetter() }?.let { stacks[index].add(it) }
            }
        }

        val mappedMoves = moves.map {
            val digits = it.split(" ")
            Move(
                count = digits[1].toInt(),
                fromStack = digits[3].toInt() - 1,
                toStack = digits[5].toInt() - 1
            )
        }

        mappedMoves.forEach { move ->
            repeat(move.count) {
                stacks[move.toStack].add(stacks[move.fromStack].removeLast())
            }
        }

        return stacks.map {
            it.lastOrNull() ?: ""
        }.joinToString("")
    }

    fun part2(input: List<String>): String {

        fun <T> MutableList<T>.removeLast(count: Int): List<T> {
            val removeIndex = this.size - count
            return List(size = count) { this.removeAt(removeIndex) }
        }

        val blankLineIndex = input.indexOfFirst { it.isBlank() }
        val crates = input.subList(0, blankLineIndex - 1)
        val moves = input.subList(blankLineIndex + 1, input.size)

        val stackNo = input[blankLineIndex - 1].last { it.isDigit() }.digitToInt()

        val stacks = List(size = stackNo) { mutableListOf<Char>() }
        crates.asReversed().forEach { crateLevel ->
            crateLevel.chunked(4).forEachIndexed { index, content ->
                content[1].takeIf { it.isLetter() }?.let { stacks[index].add(it) }
            }
        }

        val mappedMoves = moves.map {
            val digits = it.split(" ")
            Move(
                count = digits[1].toInt(),
                fromStack = digits[3].toInt() - 1,
                toStack = digits[5].toInt() - 1
            )
        }

        mappedMoves.forEach { move ->
            stacks[move.toStack].addAll(stacks[move.fromStack].removeLast(move.count))
        }

        return stacks.map {
            it.lastOrNull() ?: ""
        }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}