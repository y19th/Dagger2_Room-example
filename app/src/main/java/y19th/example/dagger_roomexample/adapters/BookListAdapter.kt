package y19th.example.dagger_roomexample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import y19th.example.dagger_roomexample.databinding.RecyclerItemBookBinding
import y19th.example.dagger_roomexample.interfaces.MainView
import y19th.example.dagger_roomexample.room.entity.Book

class BookListAdapter(
    private val mainView: MainView
    ) : BaseListAdapter<RecyclerItemBookBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RecyclerItemBookBinding> {
        tempBinding = RecyclerItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<RecyclerItemBookBinding>, position: Int) {
        val item = currentList[position]
        with(binding) {
            bookName.text = item.bookName
            teacherName.text = item.teacher
            bookDate.text = item.date
            userName.text = item.userName
            deleteButton.setOnClickListener {
                mainView.deleteFromDatabase(book = item)
            }
        }
    }
    override fun getItemCount(): Int = currentList.size

    fun updateData(newBooks: List<Book>) {
        val diffResult = DiffUtil.calculateDiff(
            DiffCallback(oldList = currentList, newList = newBooks)
        )
        tempList = newBooks
        diffResult.dispatchUpdatesTo(this)
    }
}