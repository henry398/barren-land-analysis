import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class AnalyzerTest {

    private data class Case(
        val scenario: String,
        val maxWidth: Int,
        val maxHeight: Int,
        val fields: Set<Field>,
        val expected: List<Int>
    )

    private val testCases = listOf(
        Case(
            "small farm single barren field",
            maxWidth = 5, maxHeight = 5,
            fields = setOf(Field(1, 1, 3, 3)),
            expected = listOf(16)
        ),
        Case(
            "small farm with fertile patch in center",
            maxWidth = 11, maxHeight = 11,
            fields = setOf(
                Field(2, 1, 3, 9),
                Field(7, 1, 8, 9),
                Field(1, 2, 9, 3),
                Field(1, 7, 9, 8)
            ),
            expected = listOf(9, 56)
        ),
        Case(
            "provided sample input one",
            maxWidth = 400, maxHeight = 600,
            fields = setOf(Field(0, 292, 399, 307)),
            expected = listOf(116800, 116800)
        ),
        Case(
            "provided sample input two",
            maxWidth = 400, maxHeight = 600,
            fields = setOf(
                Field(48, 192, 351, 207),
                Field(48, 392, 351, 407),
                Field(120, 52, 135, 547),
                Field(260, 52, 275, 547)
            ),
            expected = listOf(22816, 192608)
        )
    )

    @TestFactory
    fun `test barren land analysis`() =
        testCases.map {
            dynamicTest(it.scenario) {
                val analyzer = Analyzer(it.maxWidth, it.maxHeight, it.fields)
                assertEquals(it.expected, analyzer.fertileFieldAreas)
            }
        }
}
