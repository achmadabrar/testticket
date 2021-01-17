package com.achmadabrar.tickettest.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.achmadabrar.tickettest.core.di.ViewModelFactory
import com.achmadabrar.tickettest.core.di.ViewModelKey
import com.achmadabrar.tickettest.presentation.viewmodels.SearchUserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchUserViewModel::class)
    internal abstract fun mainViewModel(viewModel: SearchUserViewModel): ViewModel

}