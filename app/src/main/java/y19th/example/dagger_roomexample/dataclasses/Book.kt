package y19th.example.dagger_roomexample.dataclasses

data class Book(
    val bookName: String,
    val userName: String,
    val date: String,
    val teacher: String = "Self-Educator"
)
