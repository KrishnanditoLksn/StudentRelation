package app.ditodev.studentrelation.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    fun getSortedQuery(sortType: SortType): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM student")
        when (sortType) {
            SortType.ASCENDING -> {
                simpleQuery.append(" ORDER by name ASC")
            }

            SortType.DESCENDING -> {
                simpleQuery.append(" ORDER by name DESC")
            }

            SortType.RANDOM -> {
                simpleQuery.append(" ORDER BY RANDOM()")
            }
        }

        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}