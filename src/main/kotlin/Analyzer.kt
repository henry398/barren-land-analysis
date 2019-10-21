import java.util.ArrayDeque
import java.util.Queue

/**
 * An Analyzer holds the details about a particular farm, particularly
 * it will compute the sorted list of land areas for contiguous fertile areas
 */
class Analyzer(maxWidth: Int, maxHeight: Int, barrenFields: Set<Field>) {
    // A single field representing the entire farm.
    private val searchableBounds = Field(0, 0, maxWidth - 1, maxHeight - 1)

    // The set of all barren coordinates.
    private val barrenLand = barrenFields
        .flatMap { it.allCoordinates }
        .toSet()

    // The set of all fertile coordinates
    private val fertileLand = expandCoordinates((0 until maxWidth), (0 until maxHeight))
        .filter { !barrenLand.contains(it) }
        .toSet()

    /**
     * A sorted list of fertile land areas in square meters.
     */
    val fertileFieldAreas: List<Int>
        get() {
            val contiguousAreas = arrayListOf<Area>()

            for (coordinate in fertileLand) {
                // Skip coordinates already found in a contiguous area.
                if (!contiguousAreas.contains(coordinate)) {
                    val fertileArea = searchArea(coordinate)
                    contiguousAreas.add(fertileArea)
                }
            }

            // Get total area (number of 1m by 1m coordinates)
            // for each fertile land mass and sort.
            return contiguousAreas.map { it.size }.sorted()
        }

    // Breadth-First search around a root coordinate for nearby fertile land.
    private fun searchArea(root: Coordinate): Area {
        // Store the areas encountered in the search.
        val searched = hashSetOf(root)
        // Enqueue the root coordinate as the start of the search.
        val searchQueue = ArrayDeque<Coordinate>(fertileLand.size)
        searchQueue.add(root)

        while (!searchQueue.isEmpty()) {
            searchNeighbors(searchQueue, searched)
        }

        return searched
    }

    private fun searchNeighbors(
        searchQueue: Queue<Coordinate>,
        searched: MutableSet<Coordinate>
    ) {
        // Pop the head of the queue and search its neighbors.
        searchQueue.poll()
            .neighbors
            .filter { it.isSearchable(searched) }
            .forEach {
                // Enqueue each neighbor for searching and
                // mark it as searched.
                searchQueue.add(it)
                searched.add(it)
            }
    }

    // A coordinate gets explored if it meets three criteria:
    //   1. It is within the bounds of the farm.
    //   2. It has not already been searched in the current BFS.
    //   3. It isn't barren.
    private fun Coordinate.isSearchable(alreadySearched: Area) =
        searchableBounds.contains(this) &&
                !alreadySearched.contains(this) &&
                !barrenLand.contains(this)
}
