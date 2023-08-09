package y19th.example.dagger_roomexample.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import y19th.example.dagger_roomexample.fragment.MainFragment
import y19th.example.dagger_roomexample.room.BookDatabase
import y19th.example.dagger_roomexample.viewmodel.DbModel

@Component(modules = [TestModule::class])
interface AppComponent {
    val daggerBook: DaggerBook

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build() : AppComponent

    }

    fun inject(activity: MainFragment)

}
@Module(includes = [DatabaseModule::class])
class TestModule {

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

@Module
class DatabaseModule {
    @Provides
    fun provideDatabaseModel(database: BookDatabase): DbModel {
        return DbModel().also { it.init(database) }
    }
    @Provides
    fun provideDatabase(context: Context): BookDatabase {
        return BookDatabase.getDatabase(context = context)
    }
}
