package y19th.example.dagger_roomexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import y19th.example.dagger_roomexample.adapters.BookListAdapter
import y19th.example.dagger_roomexample.dagger.AppComponent
import y19th.example.dagger_roomexample.dagger.DaggerBook
import y19th.example.dagger_roomexample.databinding.FragmentMainBinding
import y19th.example.dagger_roomexample.extension.appComponent
import y19th.example.dagger_roomexample.fragment.sheets.FactoryDialog
import y19th.example.dagger_roomexample.interfaces.MainView
import y19th.example.dagger_roomexample.room.entity.Book
import y19th.example.dagger_roomexample.viewmodel.DbModel
import javax.inject.Inject


class MainFragment : StandardFragment<FragmentMainBinding>(),MainView {

    private val dbViewModel: DbModel by viewModels()
    private val adapter = BookListAdapter(this)

    @Inject
    lateinit var daggerBook: DaggerBook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tempBinding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
        binding.textHeader.text = daggerBook.nameBook.toString()


//
        with(binding) {
            bookList.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = adapter
            }
            getBooks()
            addButton.setOnClickListener {
                FactoryDialog(add = {book ->
                    dbViewModel.insert(context = requireContext(), book = book)
                }).show(parentFragmentManager,"factory")
            }
            refreshButton.setOnClickListener {
                dbViewModel.insert(context = requireContext())
            }
        }
    }

    private fun getBooks() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dbViewModel.apply {
                    init(requireContext())
                    books.collect {
                        submitList(it)
                    }
                }
            }
        }

    }

    private fun submitList(books: List<Book>) {
        adapter.updateData(books)
    }

    override fun deleteFromDatabase(book: Book) {
        dbViewModel.delete(requireContext(),book)
    }

}