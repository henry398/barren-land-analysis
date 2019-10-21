/**
 * Represents a coordinate in the farm for the center of a 1m by 1m square of land
 */
data class Coordinate(val x: Int, val y: Int) {
    /**
     * Returns the four neighbors touching a particular coordinate.
     * Does not include diagonal neighbors.
     */
    val neighbors: List<Coordinate>
        get() = listOf(
            this.copy(x = this.x + 1),
            this.copy(x = this.x - 1),
            this.copy(y = this.y + 1),
            this.copy(y = this.y - 1)
        )
}

/**
 * Represents a rectangular field of coordinates.
 */
data class Field(val bottomLeft: Coordinate, val topRight: Coordinate) {
    // Helper to instantiate from raw x and y values.
    constructor(x1: Int, y1: Int, x2: Int, y2: Int) :
            this(Coordinate(x1, y1), Coordinate(x2, y2))

    /**
     * Get a list of all coordinates contained in the field.
     */
    val allCoordinates: List<Coordinate>
        get() = expandCoordinates(
            xRange = bottomLeft.x..topRight.x,
            yRange = bottomLeft.y..topRight.y
        )

    /**
     * Checks if a coordinate exists inside this field.
     */
    fun contains(point: Coordinate) =
        point.x >= this.bottomLeft.x &&
                point.x <= this.topRight.x &&
                point.y >= this.bottomLeft.y &&
                point.y <= this.topRight.y
}

/**
 * An Area is just a shapeless collection of contiguous coordinates.
 */
typealias Area = Set<Coordinate>

/**
 * Checks if a coordinate is included in any area in a collection.
 */
fun Collection<Area>.contains(coordinate: Coordinate) =
    this.any { it.contains(coordinate) }

/**
 * Turns a range of x values and y values into a list of coordinates
 */
fun expandCoordinates(xRange: IntRange, yRange: IntRange) =
    xRange.flatMap { x -> yRange.map { y -> Coordinate(x, y) } }
