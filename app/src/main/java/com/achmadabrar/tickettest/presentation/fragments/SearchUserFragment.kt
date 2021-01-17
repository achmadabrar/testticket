package com.achmadabrar.tickettest.presentation.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.tickettest.R
import com.achmadabrar.tickettest.core.base.BaseFragment
import com.achmadabrar.tickettest.data.networks.NetworkState
import com.achmadabrar.tickettest.presentation.adapters.SearchUserAdapter
import com.achmadabrar.tickettest.presentation.viewmodels.SearchUserViewModel
import kotlinx.android.synthetic.main.fragment_search_user.*
import javax.inject.Inject


/**
 * Abrar
 */
class SearchUserFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: SearchUserViewModel
    lateinit var adapter: SearchUserAdapter
    private lateinit var scrollListener: RecyclerView.OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(SearchUserViewModel::class.java)

        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer {
            adapter = SearchUserAdapter(it)
            loadRecycler()
        })

        viewModel.networkLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status.equals(NetworkState.LOADED.status)) {
                tv_not_found.visibility = View.GONE
                tv_loading.visibility = View.GONE
                recycler_search_user.visibility = View.VISIBLE
            } else if (it.status.equals(NetworkState.LOADING.status)) {
                tv_not_found.visibility = View.GONE
                tv_loading.visibility = View.VISIBLE
                recycler_search_user.visibility = View.GONE
            } else if (it.status.equals(NetworkState.EMPTY.status)) {
                tv_not_found.visibility = View.GONE
                tv_loading.visibility = View.GONE
                recycler_search_user.visibility = View.GONE
            } else {
                tv_not_found.visibility = View.VISIBLE
                tv_loading.visibility = View.GONE
                recycler_search_user.visibility = View.GONE
                Toast.makeText(requireContext(), "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show()
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchUser(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchUser(newText!!)
                return true
            }

        })

        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                viewModel.setEmpty()
                return true
            }
        })
        (activity as AppCompatActivity).setSupportActionBar(toolbar_search_user)
        (activity as AppCompatActivity).supportActionBar?.title = "Github User Finder"

    }

    fun loadRecycler() {
        recycler_search_user.adapter = adapter
        recycler_search_user.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition: Int = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    recycler_search_user.removeOnScrollListener(scrollListener)
                }
            }
        }
        recycler_search_user.addOnScrollListener(scrollListener)
    }
}