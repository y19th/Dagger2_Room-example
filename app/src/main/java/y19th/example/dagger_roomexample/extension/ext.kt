package y19th.example.dagger_roomexample.extension

import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.navigation.findNavController

fun View.makeGone() {
    this.visibility = View.GONE
}
fun View.makeVisible() {
    this.visibility = View.VISIBLE
}
fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.navigateTo(id: Int) {
    this.findNavController().navigate(id)
}

fun View.navigateUp() {
    this.findNavController().navigateUp()
}

fun Group.setOnClick(listener: (View) -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}