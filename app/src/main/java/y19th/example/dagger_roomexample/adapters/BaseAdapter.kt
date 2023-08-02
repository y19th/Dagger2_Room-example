package y19th.example.dagger_roomexample.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseAdapter<T: ViewBinding> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var tempBinding: T? = null
    val binding: T get() = requireNotNull(tempBinding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return super.createViewHolder(parent,viewType)
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {}

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position
}