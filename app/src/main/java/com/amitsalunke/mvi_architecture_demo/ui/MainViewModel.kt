package com.amitsalunke.mvi_architecture_demo.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.amitsalunke.mvi_architecture_demo.model.Blog
import com.amitsalunke.mvi_architecture_demo.repository.MainRepository
import com.amitsalunke.mvi_architecture_demo.util.DataState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    //when there is process death following savedStateHandle will handle the situation
    // val items : LiveData<ArrayList<String>> = savedStateHandle.getLiveData(KEY, arrayListOf())
    //to save them savedStateHandle.set(KEY, items.value)
    //in main act or frag  viewModel = ViewModelProviders.of(this, SavedStateViewModelFactory(this)).get(
    //        ShoppingListViewModel::class.java)

    //while using only LiveData
    //private var _dataState2: LiveData<DataState<List<Blog>>> = MutableLiveData()

    //take MainStateEvents and convert that into _dataState events
    //getter function for dataState
    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState // as the above _dataState is private this getter function is acesser for it


    //when using only mvvm use following
    /*fun getBlogs(): LiveData<DataState<List<Blog>>> {
        loadData()
        return _dataState
    }

    //while using flow
    private fun loadData() {
        viewModelScope.launch {
            mainRepository.getBlog()
                .onEach { dataState -> _dataState.value = dataState }
                .launchIn(viewModelScope)
        }
    }*/

    //while using live data and not flow
     /*fun getLiveDataBlogs(): LiveData<DataState<List<Blog>>> {
        viewModelScope.launch {
            _dataState2 = mainRepository.getBlog2()
        }

        return _dataState2
    }*/


    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch(IO) {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogEvents -> {
                    mainRepository.getBlog()
                        .onEach { dataState -> _dataState.value = dataState } //.value is nothing but setValue
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {
                    //no need
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetBlogEvents : MainStateEvent()
    object None : MainStateEvent()
}