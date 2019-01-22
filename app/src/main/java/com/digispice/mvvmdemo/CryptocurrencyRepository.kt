package com.digispice.mvvmdemo

import android.util.Log
import com.digispice.mvvmdemo.data.Cryptocurrency
import com.digispice.mvvmdemo.db.CryptocurrenciesDao
import com.digispice.mvvmdemo.webservices.ApiInterface
import io.reactivex.Observable
import javax.inject.Inject

class CryptocurrencyRepository @Inject constructor(
    val apiInterface: ApiInterface,
    val cryptocurrenciesDao: CryptocurrenciesDao
) {
    fun getCryptocurrencies(): Observable<List<Cryptocurrency>> {
        val observableFromApi = getCryptocurrenciesFromApi()
        val observableFromDb = getCryptocurrenciesFromDb()
        return Observable.concatArrayEager(observableFromApi, observableFromDb)
    }

    fun getCryptocurrenciesFromApi(): Observable<List<Cryptocurrency>> {
        return apiInterface.getCryptocurrencies("0")
            .doOnNext {
                Log.e("REPOSITORY API * ", it.size.toString())
                for (item in it) {
                    cryptocurrenciesDao.insertCryptocurrency(item)
                }
            }
    }

    fun getCryptocurrenciesFromDb(): Observable<List<Cryptocurrency>> {
        return cryptocurrenciesDao.queryCryptocurrencies()
            .toObservable()
            .doOnNext {
                //Print log it.size :)
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
    }
}

