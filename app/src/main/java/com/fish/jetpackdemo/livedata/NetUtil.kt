package com.fish.jetpackdemo.livedata

object NetUtil {

    lateinit var listener : InfoNotify

    fun getUserInfo(notify: InfoNotify) {
        listener = notify
        Thread {
            Thread.sleep(2000)
            listener?.notify(100)
        }
    }

    interface InfoNotify {
        fun notify(a : Int)
    }
}