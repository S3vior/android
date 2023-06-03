package com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment

import android.R
import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.databinding.FragmentHomeBinding
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    RecyclerViewInteractionListener {

    private val personViewModel: PersonViewModel by viewModels()
    private fun initViewModel() {
        binding.viewModel = personViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.edSearch.setOnEditorActionListener { text, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (text.text.toString() == "") {
                    personViewModel.getAllPersons()
                } else {
                    personViewModel.searchFoePerson(text.text.toString())
                }
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
            }
            true
        }
    }

    private fun initSpinner() {
        spinner(Constants.StateFilter.state, binding.spinner)
    }

    private fun spinner(array: Array<String>, spinner: Spinner) {
        val arrayAdapter =
            ArrayAdapter(requireActivity(), R.layout.simple_spinner_item, array.toList())
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> personViewModel.getAllPersons()
                    1 -> personViewModel.getMissedPersons()
                    2 -> personViewModel.getFoundedPersons()


                }

            }

        }

//        when (spinner.selectedItem.toString()) {
//            Constants.StateFilter.state[0] -> personViewModel.getMissedPersons()
//            Constants.StateFilter.state[1] -> personViewModel.getFoundedPersons()
//            Constants.StateFilter.state[2] -> personViewModel.getScrapedPersons()
//        }
    }

    override fun callFunctions() {
        initViewModel()
        recyclerAdapter()
        initSpinner()
        binding.profile.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(com.example.s3vior.R.id.action_homeFragment_to_moreFragment)
         }

    }

    override fun <T> onClickItem(view: T) {

        view as MafqoudModel

        val action = HomeFragmentDirections.actionHomeFragmentToAllPersonInfo(view.id!!)
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun recyclerAdapter() {

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = HomeItemAdapter(mutableListOf(), this)

    }


}
