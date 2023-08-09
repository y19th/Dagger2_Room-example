package y19th.example.dagger_roomexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import y19th.example.dagger_roomexample.adapters.BookAdapter
import y19th.example.dagger_roomexample.dagger.DaggerBook
import y19th.example.dagger_roomexample.databinding.FragmentMainBinding
import y19th.example.dagger_roomexample.extension.makeGone
import y19th.example.dagger_roomexample.extension.makeVisible
import y19th.example.dagger_roomexample.fragment.sheets.FactoryDialog
import y19th.example.dagger_roomexample.interfaces.MainView
import y19th.example.dagger_roomexample.room.entity.Book
import y19th.example.dagger_roomexample.viewmodel.DbModel
import javax.inject.Inject


class MainFragment : StandardFragment<FragmentMainBinding>(),MainView {

    private val adapter = BookAdapter(this)

    @Inject /*test injection of dagger book*/
    lateinit var daggerBook: DaggerBook

    @Inject
    lateinit var database: DbModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent.inject(this)
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
        with(binding) {
            bookList.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = adapter
            }
            getBooks()
            addButton.setOnClickListener {
                FactoryDialog(add = { book ->
                    database.insert(context = requireContext(), book = book)
                }).show(parentFragmentManager, "factory")
            }
            refreshButton.setOnClickListener {
                database.insert(context = requireContext())
            }
        }
    }

    private fun getBooks() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                database.also {
                    it.init(context = requireContext())
                }.books.collect {
                    onEmptyList(it.isEmpty())
                    submitList(it)
                }
            }
        }

    }

    private fun onEmptyList(enable: Boolean) {
        when(enable) {
            true -> binding.nullListText.makeVisible()
            false -> binding.nullListText.makeGone()
        }
    }

    private fun submitList(books: List<Book>) {
        adapter.updateData(books)
    }

    override fun deleteFromDatabase(book: Book) {
        database.delete(requireContext(),book)
    }

}