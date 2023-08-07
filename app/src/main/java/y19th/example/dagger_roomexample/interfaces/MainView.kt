package y19th.example.dagger_roomexample.interfaces

import y19th.example.dagger_roomexample.room.entity.Book

interface MainView {
    fun deleteFromDatabase(book: Book)
}