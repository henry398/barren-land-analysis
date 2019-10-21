/**
 * Parse a sequence of strings to a set of barren fields.
 * Each string should be a list of 4 integers, separated by a single space.
 */
fun Sequence<String>.toFields() =
    this.takeWhile { !it.isBlank() }
        .map(::parseField)
        .toSet()

// Parse a particular space delimited input string.
// Fails if it cannot find exactly 4 integers in the string.
private fun parseField(input: String): Field {
    val parsed = input
        .split(" ")
        .mapNotNull(String::toIntOrNull)

    require(parsed.size == 4) {
        "Unable to parse coordinates for field $input!"
    }

    val (x1, y1, x2, y2) = parsed
    return Field(x1, y1, x2, y2)
}
