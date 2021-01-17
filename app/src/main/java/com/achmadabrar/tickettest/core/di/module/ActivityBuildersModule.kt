package com.achmadabrar.tickettest.core.di.module

import com.achmadabrar.tickettest.presentation.activities.SearchUserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): SearchUserActivity
}