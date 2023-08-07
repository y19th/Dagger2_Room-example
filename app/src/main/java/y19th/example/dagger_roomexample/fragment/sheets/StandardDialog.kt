package y19th.example.dagger_roomexample.fragment.sheets

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import y19th.example.dagger_roomexample.R

open class StandardDialog<T> : BottomSheetDialogFragment() {
    var tempBinding: T? = null
    val binding: T get() = requireNotNull(tempBinding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            (it as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dialog
    }


    override fun onDestroy() {
        tempBinding = null
        super.onDestroy()
    }
}