package y19th.example.dagger_roomexample

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import y19th.example.dagger_roomexample.extension.makeGone

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onStart() {
        findViewById<AppCompatTextView>(R.id.app_name_header).makeGone()
        super.onStart()
    }
}