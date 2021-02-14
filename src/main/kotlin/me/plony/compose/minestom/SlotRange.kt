package me.plony.compose.minestom

public data class SlotRange(val columns: IntRange, val rows: IntRange) {
    private fun slot(x: Int, y: Int): Pair<Int, Int> = columns.first + x to rows.first + y
    public fun subSlots(columns: IntRange, rows: IntRange): SlotRange {
        val (columnsStart, rowsStart) = slot(columns.first, rows.first)
        val (columnsEnd, rowsEnd) = slot(columns.last, rows.last)
        require(columnsStart in this.columns && columnsEnd in this.columns)
        require(rowsStart in this.rows && rowsEnd in this.rows)
        return SlotRange(
            columnsStart..columnsEnd,
            rowsStart..rowsEnd
        )
    }

}