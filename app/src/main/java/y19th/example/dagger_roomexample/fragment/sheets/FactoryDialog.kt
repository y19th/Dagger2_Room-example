package y19th.example.dagger_roomexample.fragment.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import y19th.example.dagger_roomexample.databinding.FragmentDialogFactoryBinding
import y19th.example.dagger_roomexample.extension.textCheckNull
import y19th.example.dagger_roomexample.room.entity.Book

class FactoryDialog(val add: (Book) -> Unit) : StandardDialog<FragmentDialogFactoryBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tempBinding = FragmentDialogFactoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            doneButton.setOnClickListener {
                if (bookNameInput.text != null) {
                    add.invoke(
                        Book(
                            id = 0,
                            bookName = bookNameInput.textCheckNull(),
                            userName = userNameInput.textCheckNull(replace = "anonymous"),
                            teacher = teacherNameInput.textCheckNull(replace = "self education"),
                            date = dateInput.textCheckNull(replace = "to be announced")
                        )
                    )
                    dismiss()
                }
            }
        }
    }
}