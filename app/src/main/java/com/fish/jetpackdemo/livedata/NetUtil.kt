package com.fish.jetpackdemo.livedata

object NetUtil {

    //接口
    var listener : InfoNotify ? = null

    fun getUserInfo(notify: InfoNotify) {
        listener = notify
        Thread {
            //模拟获取网络数据
            Thread.sleep(2000)
            //回调通知更新
            listener?.notify(100)
        }.start()
    }

    interface InfoNotify {
        fun notify(a : Int)
    }

    fun removeListener() : Unit {
        listener?.let { listener = null }
    }
}