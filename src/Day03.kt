fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val rucksack = convertToRucksack(it)
            val item = findSameItem(rucksack)
            getItemPriority(item)
        }
    }

    fun part2(input: List<String>): Int {
        return createElfGroupList(input).sumOf {
            getItemPriority(getSameItemFromGroups(it))
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private fun createElfGroupList(input: List<String>): MutableList<MutableList<String>> {
    val elfGroupList: MutableList<MutableList<String>> = mutableListOf()
    var group: MutableList<String> = mutableListOf()
    input.forEachIndexed { index, string ->
        if (index % 3 == 0) {
            group = mutableListOf()
        }
        group.add(string)
        if (index % 3 == 2) {
            elfGroupList.add(group)
        }
    }
    return elfGroupList
}

fun getSameItemFromGroups(group: List<String>): Char {
    val mergedString = group.joinToString("")
    val groupedList = mergedString.groupBy { it }
    val filteredList = groupedList.filter { it.value.size >= 3 }.map { it.key }

    filteredList.forEach {
        if (
            group[0].contains(it) &&
            group[1].contains(it) &&
            group[2].contains(it)
        ) {
            return it
        }
    }
    return 'a'
}

fun convertToRucksack(string: String): Rucksack {
    return Rucksack(
        compartment1 = string.take(string.length / 2),
        compartment2 = string.takeLast(string.length / 2)
    )
}

fun findSameItem(rucksack: Rucksack): Char {
    with(rucksack) {
        return compartment1.first {
            compartment2.contains(it)
        }
    }
}

fun getItemPriority(item: Char): Int {
    return if (item.isLowerCase()) {
        item - 'a' + 1
    } else {
        item - 'A' + 27
    }
}

class Rucksack(val compartment1: String, val compartment2: String)