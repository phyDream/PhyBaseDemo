package com.demo.phy.phybasedemo.mvp_presenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.data.bean.MovieDoubanResponseBean
import com.demo.phy.phybasedemo.mvp_model.MovieModel
import com.demo.phy.phybasedemo.mvp_view.MovieTypeView
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieTypePresenter(view:MovieTypeView):BasePresenter<MovieTypeView>(view){
    var movieModel: MovieModel? = null
    init {
        movieModel = MovieModel.newInstance()
    }

    //获取数据
    fun getMovieList(id: String?, start:Int){
        var observer = object : Observer<MovieDoubanResponseBean> {
            override fun onNext(t: MovieDoubanResponseBean) {
                var list: MutableList<MovieDoubanResponseBean.MovieBeanInDouBan>? = t?.subjects
//                LogUtils.i("~list~"+list.toString())
                //对数据处理
                Observable.create<MutableList<MovieDoubanResponseBean.MovieBeanInDouBan>> {
                    s ->
                    s.onNext(list!!)
                    s.onComplete()
                }
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.io())
                        .toList()
                        .map { it ->
                            //存数据
                            it
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { it ->
                            view.complete(list!!)
                        }

            }

            override fun onError(e: Throwable) {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
            }

        }

        movieModel?.getMovie(id,start,observer)
    }
}

private fun <T> ObservableSource<T>.subscribe(t: T) {

}
