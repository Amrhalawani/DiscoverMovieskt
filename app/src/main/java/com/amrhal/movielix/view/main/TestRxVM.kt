package com.amrhal.movielix.view.main

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


class TestRxVM : ViewModel() {
    private val disposables = CompositeDisposable()

//
//
//    fun postForgotPassword(email: String): MutableLiveData<ForgotPasswordRes> {
//        val data = MutableLiveData<ForgotPasswordRes>()
//        disposables.add(
//            ServiceRepo.forgotPassword(email)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    data.value = it
//                }, { throwable ->
//
//                    //todo bug here on api, check it again after the api team solve their issue
//                    Log.e("tag", "forgot password throwable: ${throwable.message}")
//                    val error: HttpException = throwable as HttpException
//                    if (error.code() >= 400) {
//                        val jObj = error.response().errorBody()!!.string()
//                        val gson = Gson()
//                        data.value = gson.fromJson(jObj, ForgotPasswordRes::class.java)
//                    }
//
//                })
//        )
//        return data
//    }
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}