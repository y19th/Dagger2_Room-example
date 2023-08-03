package y19th.example.dagger_roomexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import y19th.example.dagger_roomexample.R
import y19th.example.dagger_roomexample.databinding.FragmentFactoryBookBinding
import y19th.example.dagger_roomexample.extension.navigateTo

class FactoryBookFragment : StandardFragment<FragmentFactoryBookBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tempBinding = FragmentFactoryBookBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
//            TODO(group those listeners)
            backButton.setOnClickListener {
                view.navigateTo(R.id.factory_to_main)
            }
            textHeader.setOnClickListener {
                view.navigateTo(R.id.factory_to_main)
            }

        }
    }
}