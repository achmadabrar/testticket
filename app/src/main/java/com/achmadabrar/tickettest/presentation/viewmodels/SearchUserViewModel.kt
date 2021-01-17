package com.achmadabrar.tickettest.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.tickettest.core.base.BaseViewModel
import com.achmadabrar.tickettest.data.models.Item
import com.achmadabrar.tickettest.data.networks.GithubApiService
import com.achmadabrar.tickettest.data.networks.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ONE_HOUR_CACHE = 3600 * 1000

class SearchUserViewModel @Inject constructor(
    val githubApiService: GithubApiService
): BaseViewModel<SearchUserViewModel>() {

    val networkLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    private val supervisorJob = SupervisorJob()
    val usersLiveData: MutableLiveData<List<Item>> = MutableLiveData()

    fun searchUser(query: String) {
        if (query.length == 0) {
            networkLiveData.postValue(NetworkState.EMPTY)
        } else {
            ioScope.launch(getJobErrorHandler() +  supervisorJob) {
                val users = githubApiService.getUser(query)
                if (users.items.isNotEmpty()) {
                    networkLiveData.postValue(NetworkState.LOADED)
                    usersLiveData.postValue(users.items)
                } else {
                    networkLiveData.postValue(NetworkState.FAILED)
                }
            }
        }
    }

    fun setEmpty() {
        networkLiveData.postValue(NetworkState.EMPTY)
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(SearchUserViewModel::class.simpleName, "An error happened: $e")
        networkLiveData.postValue(NetworkState.fialed(e.localizedMessage))
        networkLiveData.postValue(NetworkState.FAILED)
    }
    
}