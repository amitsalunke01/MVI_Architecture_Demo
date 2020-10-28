package com.amitsalunke.mvi_architecture_demo.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.amitsalunke.mvi_architecture_demo.repository.MainRepository


class MainViewModel @ViewModelInject
constructor(
    private val repository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
}