package com.digispice.mvvmdemo.module

import com.digispice.mvvmdemo.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
        abstract fun contributeMainActivity():MainActivity
}