package y19th.example.dagger_roomexample.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import y19th.example.dagger_roomexample.room.entity.Book

open class BaseListAdapter<T: ViewBinding>:  RecyclerView.Adapter<BaseViewHolder<T>>() {

    var tempBinding: T? = null
    val binding: T get() = requireNotNull(tempBinding)

    var tempList = emptyList<Book>()
    val currentList: List<Book> get() = tempList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return super.createViewHolder(parent,viewType)
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {}

    override fun getItemId(position: Int): Long {
        return currentList[position].id.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].id
    }

    class DiffCallback(
        private val newList: List<Book>,
        private val oldList: List<Book>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return newList[newItemPosition].id == oldList[oldItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return newList[newItemPosition] == oldList[oldItemPosition]
        }

    }
}