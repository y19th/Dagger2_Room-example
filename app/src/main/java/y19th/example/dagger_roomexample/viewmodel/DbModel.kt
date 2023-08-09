package y19th.example.dagger_roomexample.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import y19th.example.dagger_roomexample.extension.shortToast
import y19th.example.dagger_roomexample.room.BookDatabase
import y19th.example.dagger_roomexample.room.entity.Book
import javax.inject.Inject
import kotlin.random.Random

class DbModel @Inject constructor(): ViewModel() {

    private val _books = MutableStateFlow(listOf<Book>())
    val books: StateFlow<List<Book>> = _books.asStateFlow()

    private var _database: BookDatabase? = null
    private val database: BookDatabase get() = requireNotNull(_database)

    fun init(context: Context) {
        viewModelScope.launch(Dispatchers.Default) {
            _database = BookDatabase.getDatabase(context = context)
        }.invokeOnCompletion { getBooks() }
    }

    private fun getBooks() {
        viewModelScope.launch(Dispatchers.Default) {
            val result = database.bookDao().getBooks()
            _books.update {
                result
            }
        }
    }

    fun insert(context: Context, book: Book? = null) {
        val ran = Random(System.currentTimeMillis()).nextInt(1000)
        try {
            viewModelScope.launch(Dispatchers.Default) {
                database.bookDao()
                    .insertBooks(book ?: Book(id = 0, userName = "book$ran", bookName = "asd$ran", date = "random$ran"))
            }.invokeOnCompletion {
                getBooks()
            }
        } catch (e: Exception) {
            context.shortToast(e.toString())
        }
    }

    fun delete(context: Context,book: Book) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                database.bookDao().deleteBook(book)
            }.invokeOnCompletion {
                getBooks()
            }
        } catch (e: Exception) {
            context.shortToast(e.toString())
        }
    }

}