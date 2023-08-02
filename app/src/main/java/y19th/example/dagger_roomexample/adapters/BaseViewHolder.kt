package y19th.example.dagger_roomexample.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder<T: ViewBinding> (binding: T) : RecyclerView.ViewHolder(binding.root) {
}