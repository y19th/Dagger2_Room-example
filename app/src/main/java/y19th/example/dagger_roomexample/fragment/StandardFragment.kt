package y19th.example.dagger_roomexample.fragment

import androidx.fragment.app.Fragment
import y19th.example.dagger_roomexample.extension.appComponent

open class StandardFragment<T> : Fragment() {


    val mainComponent get() =  requireContext().appComponent

    var tempBinding: T? = null
    val binding: T get() = requireNotNull(tempBinding)

    override fun onDestroy() {
        tempBinding = null
        super.onDestroy()
    }
}