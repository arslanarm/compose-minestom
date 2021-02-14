package me.plony.compose.minestom

data class SlotRange(val columns: IntRange, val rows: IntRange) {
    fun slot(x: Int, y: Int) = columns.first + x to rows.first + y
    fun subSlots(columns: IntRange, rows: IntRange): SlotRange {
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