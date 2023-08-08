package y19th.example.dagger_roomexample.dagger

import android.app.Activity
import dagger.Component
import dagger.Module
import dagger.Provides
import y19th.example.dagger_roomexample.fragment.MainFragment

@Component(modules = [AppModule::class])
interface AppComponent {
    val daggerBook: DaggerBook

    fun inject(activity: MainFragment)
}
@Module
object AppModule {

    @Provides
    fun provideBook(
        user: User,
        date: Date,
        nameBook: NameBook,
        teacher: Teacher
    ) : DaggerBook {
        return DaggerBook(
            user = user,
            date = date,
            nameBook = nameBook,
            teacher = teacher
        )
    }

    @Provides
    fun provideUser() : User {
        return User()
    }

    @Provides
    fun provideDate(): Date {
        return Date()
    }

    @Provides
    fun provideNameBook() : NameBook {
        return NameBook()
    }

    @Provides
    fun provideTeacher() : Teacher {
        return Teacher()
    }

}