package y19th.example.dagger_roomexample.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "book_name") val bookName: String,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "book_date") val date: String,
    @ColumnInfo(name = "teacher_name") val teacher: String = "self-educator"
) {
    override fun equals(other: Any?): Boolean {
        other as Book
        return when {
            id != other.id -> {
                false
            }
            bookName != other.bookName -> {
                false
            }
            userName != other.userName -> {
                false
            }
            date != other.date -> {
                false
            }
            teacher != other.teacher -> {
                false
            }
            else -> true
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}