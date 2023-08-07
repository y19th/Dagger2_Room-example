package y19th.example.dagger_roomexample.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import y19th.example.dagger_roomexample.room.entity.Book

@Dao
interface BookDao {

    @Query("select * from book")
    fun getBooks() : List<Book>

    @Insert
    fun insertBooks(vararg books: Book)

    @Delete
    fun deleteBook(book: Book)
}