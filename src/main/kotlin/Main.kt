import kotlin.system.exitProcess

/**
 * Run barren land analysis according to the problem statement.
 */
fun main() {
    val barrenFields = promptInput()
    // Create an analyzer for the given input
    val analyzer = Analyzer(400, 600, barrenFields)
    // Search the fields for fertile land
    val results = analyzer.fertileFieldAreas
    println(
        "The areas of fertile land are:\n" +
                results.joinToString(separator = " ")
    )
}

private fun promptInput(): Set<Field> {
    println("Please input field coordinates:")

    try {
        // read each line from stdin and parses them
        // to a set of barren fields
        return generateSequence(::readLine).toFields()
    } catch (e: IllegalArgumentException) {
        // Parse failure.
        System.err.println(e.message)
        exitProcess(1)
    }
}
