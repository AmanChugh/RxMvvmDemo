package com.digispice.mvvmdemo

import android.app.Activity
import android.app.Application
import com.digispice.mvvmdemo.component.AppComponent
import com.digispice.mvvmdemo.component.DaggerAppComponent
import com.digispice.mvvmdemo.module.AppModule
import com.digispice.mvvmdemo.module.NetModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication: Application(),HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> =activityInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(""))
            .build().inject(this)
    }
}