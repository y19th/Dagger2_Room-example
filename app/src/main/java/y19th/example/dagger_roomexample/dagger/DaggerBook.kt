package y19th.example.dagger_roomexample.dagger


data class DaggerBook(
    val user: User,
    val teacher: Teacher,
    val nameBook: NameBook,
    val date: Date
)


class User {
    override fun toString(): String {
        return "dagger user"
    }
}
class Teacher {
    override fun toString(): String {
        return "dagger teacher"
    }
}

class NameBook {
    override fun toString(): String {
        return "dagger book"
    }
}

class Date {
    override fun toString(): String {
        return "dagger date"
    }
}