package com.achmadabrar.tickettest.core.di.module

import com.achmadabrar.tickettest.presentation.fragments.SearchUserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchUserModule(): SearchUserFragment
}