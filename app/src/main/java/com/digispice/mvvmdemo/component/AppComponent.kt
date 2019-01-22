package com.digispice.mvvmdemo.component

import android.app.Application
import com.digispice.mvvmdemo.module.AppModule
import com.digispice.mvvmdemo.module.BuildersModule
import com.digispice.mvvmdemo.module.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, BuildersModule::class,NetModule::class))
interface AppComponent {

    fun inject(app: Application)
}