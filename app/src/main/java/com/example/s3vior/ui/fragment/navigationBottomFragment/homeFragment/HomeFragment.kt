package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentHomeBinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    RecyclerViewInteractionListener {

    private val personViewModel: PersonViewModel by activityViewModels()

    private fun initViewModel() {
        binding.viewModel = personViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.edSearch.setOnEditorActionListener { text, actionId, _ ->
            if (actionId != EditorInfo.IME_NULL) {
                if (text.text.toString()  == ""){
                    personViewModel.getAllPersons()
                }
                else{
                    personViewModel.searchFoePerson(text.text.toString())
                }
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
            }
            true
        }
    }

    private fun recyclerAdapter() {

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = HomeItemAdapter(mutableListOf(), this)

    }

    override fun callFunctions() {
        initViewModel()
        recyclerAdapter()

    }

    override fun <T> onClickItem(view: T) {
        view as Person
        findNavController().navigate(R.id.action_homeFragment_to_allPersonInfo)
    }


}
