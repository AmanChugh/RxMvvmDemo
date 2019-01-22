package com.digispice.mvvmdemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.digispice.mvvmdemo.data.Cryptocurrency
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cryptocurrenciesViewModelFactory: CryptocurrenciesViewModelFactory
    lateinit var cryptocurrenciesViewModel: CryptocurrenciesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        cryptocurrenciesViewModel = ViewModelProviders.of(this, cryptocurrenciesViewModelFactory).get(
            CryptocurrenciesViewModel::class.java
        )

        cryptocurrenciesViewModel.loadCryptocurrencies()

        cryptocurrenciesViewModel.cryptocurrenciesResult().observe(this,
            Observer<List<Cryptocurrency>> {
                hello_world_textview.text = "Hello ${it?.size} cryptocurrencies"
            })

        cryptocurrenciesViewModel.cryptocurrenciesError().observe(this, Observer<String> {
            hello_world_textview.text = "Hello error $it"
        })
    }

    override fun onDestroy() {
        cryptocurrenciesViewModel.disposeElements()
        super.onDestroy()
    }
}

//    private fun showCryptocurrencies() {
//        val cryptocurrenciesResponse = getCryptocurrencies()
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//
//        val disposableObserver =
//            cryptocurrenciesResponse.subscribeWith(object : DisposableObserver<List<Cryptocurrency>>() {
//                override fun onComplete() {
//                }
//
//                override fun onNext(cryptocurrencies: List<Cryptocurrency>) {
//                    val listSize = cryptocurrencies.size
//                    Log.e("ITEMS **** ", cryptocurrencies.toString())
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.e("ERROR *** ", e.message)
//                }
//
//            })
//
//        compositeDisposable.addAll(disposableObserver)
//
//    }
//
//    private fun getCryptocurrencies(): Observable<List<Cryptocurrency>> {
//        val retrofit = ApiClient.getClient()
//        val apiInterface = retrofit.create(ApiInterface::class.java)
//        return apiInterface.getCryptocurrencies("0")
//    }
//
//    override fun onDestroy() {
//        compositeDisposable.dispose()
//        super.onDestroy()
//    }
