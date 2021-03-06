package uz.minmax.sampledaggerapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import uz.minmax.sampledaggerapp.R
import uz.minmax.sampledaggerapp.data.models.Customer
import uz.minmax.sampledaggerapp.ui.adapter.CustomerListAdapter
import uz.minmax.sampledaggerapp.ui.viewmodel.CustomerListViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CustomerListFragment : Fragment(), CustomerListAdapter.ICustomerClickListener {

    @Inject lateinit var customerViewModel: CustomerListViewModel

    lateinit var addNewButton: FloatingActionButton

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (activity as MainActivity).customerComponent.inject(this)
    }

    //ui
    private lateinit var customerListView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_customer_list, container, false)

        customerListView = view.findViewById(R.id.list_view)
        addNewButton = view.findViewById(R.id.add_customer_btn)

        addNewButton.setOnClickListener {findNavController().navigate(R.id.newCustomerFragment) }

        customerViewModel.customers.observe(viewLifecycleOwner, Observer {customers->
            customerListView.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                it.adapter = CustomerListAdapter(this,customers)
                it.setHasFixedSize(true)
            }
        })

        return view
    }

    override fun onClickListener(view: View, customer: Customer?) {
//        findNavController().navigate(R.id.action_customerListFragment_to_customerFragment)
    }
}
