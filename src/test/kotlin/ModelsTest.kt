import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ModelsTest {
    @Test
    fun `coordinate neighbors`() {
        assertEquals(
            listOf(
                Coordinate(6, 5),
                Coordinate(4, 5),
                Coordinate(5, 6),
                Coordinate(5, 4)
            ),
            Coordinate(5, 5).neighbors
        )
    }

    @Test
    fun `get all coordinates contained in a field`() {
        assertEquals(
            listOf(
                Coordinate(1, 1),
                Coordinate(1, 2),
                Coordinate(2, 1),
                Coordinate(2, 2)
            ),
            Field(1, 1, 2, 2).allCoordinates
        )
    }

    @Test
    fun `field contains coordinate`() {
        assertTrue(
            Field(1, 1, 3, 3).contains(Coordinate(2, 2))
        )
    }

    @Test
    fun `field does not contain coordinate`() {
        assertFalse(
            Field(1, 1, 3, 3).contains(Coordinate(4, 4))
        )
    }

    @Test
    fun `area list contains coordinate`() {
        assertTrue(
            listOf(
                setOf(Coordinate(1, 1)),
                setOf(Coordinate(2, 2), Coordinate(3, 3))
            ).contains(Coordinate(1, 1))
        )
    }

    @Test
    fun `area list does not contain coordinate`() {
        assertFalse(
            listOf(
                setOf(Coordinate(1, 1)),
                setOf(Coordinate(2, 2), Coordinate(3, 3))
            ).contains(Coordinate(4, 4))
        )
    }
}
