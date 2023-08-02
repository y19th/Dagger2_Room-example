package y19th.example.dagger_roomexample.fragment

import androidx.fragment.app.Fragment

open class StandardFragment<T> : Fragment() {

    var tempBinding: T? = null
    val binding: T get() = requireNotNull(tempBinding)

    override fun onDestroy() {
        tempBinding = null
        super.onDestroy()
    }
}