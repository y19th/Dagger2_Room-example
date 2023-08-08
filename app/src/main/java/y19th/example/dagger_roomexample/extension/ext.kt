package y19th.example.dagger_roomexample.extension

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.navigation.findNavController
import y19th.example.dagger_roomexample.MainApp
import y19th.example.dagger_roomexample.dagger.AppComponent

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

fun View.navigateTo(id: Int,bundle: Bundle) {
    this.findNavController().navigate(id,bundle)
}

fun View.navigateUp() {
    this.findNavController().navigateUp()
}

fun Group.setOnClick(listener: (View) -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

fun Context.shortToast(msg: String) {
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun String.checkNull(replace: String = "null") : String {
    return this.ifEmpty { replace }
}

fun EditText.textCheckNull(replace: String = "null"): String {
    return this.text.toString().checkNull(replace)
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }