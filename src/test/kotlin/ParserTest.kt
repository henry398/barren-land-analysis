import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParserTest {
    @Test
    fun `successfully parse fields in first example`() {
        assertEquals(
            sequenceOf("0 292 399 307").toFields(),
            setOf(Field(0, 292, 399, 307))
        )
    }

    @Test
    fun `successfully parse fields in second example`() {
        assertEquals(
            setOf(
                Field(48, 192, 351, 207),
                Field(48, 392, 351, 407),
                Field(120, 52, 135, 547),
                Field(260, 52, 275, 547)
            ),
            sequenceOf(
                "48 192 351 207",
                "48 392 351 407",
                "120 52 135 547",
                "260 52 275 547"
            ).toFields()
        )
    }

    @Test
    fun `parse failure - noninteger input`() {
        assertThrows<IllegalArgumentException> {
            sequenceOf("foobar").toFields()
        }
    }

    @Test
    fun `parse failure - missing input`() {
        assertThrows<IllegalArgumentException> {
            sequenceOf("123 123").toFields()
        }
    }
}
