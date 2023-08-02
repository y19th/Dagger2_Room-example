package y19th.example.dagger_roomexample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import y19th.example.dagger_roomexample.databinding.RecyclerItemBookBinding
import y19th.example.dagger_roomexample.dataclasses.Book

class BookListAdapter(private val bookList: List<Book>) : BaseAdapter<RecyclerItemBookBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RecyclerItemBookBinding> {
        tempBinding = RecyclerItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<RecyclerItemBookBinding>, position: Int) {
        with(binding) {
            bookName.text = bookList[position].bookName
            teacherName.text = bookList[position].teacher
            bookDate.text = bookList[position].date
            userName.text = bookList[position].userName
        }
    }

    override fun getItemCount(): Int = bookList.size
}