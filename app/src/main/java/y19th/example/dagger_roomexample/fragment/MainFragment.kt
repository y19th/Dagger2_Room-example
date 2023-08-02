package y19th.example.dagger_roomexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import y19th.example.dagger_roomexample.adapters.BookListAdapter
import y19th.example.dagger_roomexample.databinding.FragmentMainBinding
import y19th.example.dagger_roomexample.dataclasses.Book

class MainFragment : StandardFragment<FragmentMainBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tempBinding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            bookList.adapter = BookListAdapter(getBooks())
            bookList.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun getBooks(): List<Book> {
        return listOf(
            Book(bookName = "gym", userName = "oleg", date = "tomorrow"),
            Book(bookName = "football", userName = "vika", date = "3 days left"),
            Book(bookName = "gym", userName = "pop", date = "10 days left"),
            Book(bookName = "baseball", userName = "oleg", date = "2 days left"),
        )
    }
}