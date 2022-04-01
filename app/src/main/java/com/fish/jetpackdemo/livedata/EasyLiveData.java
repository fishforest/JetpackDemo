package com.fish.jetpackdemo.livedata;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class EasyLiveData<T> extends LiveData<T> {

    @Override
    public void observe(@NonNull @NotNull LifecycleOwner owner, @NonNull @NotNull Observer<? super T> observer) {
        super.observe(owner, new EasyObserver<>(observer));
    }

    @Override
    public void observeForever(@NonNull @NotNull Observer<? super T> observer) {
        super.observeForever(new EasyObserver<>(observer));
    }

    @Override
    protected void setValue(T value) {
        super.setValue(value);
    }

    @Override
    protected void postValue(T value) {
        super.postValue(value);
    }

    class EasyObserver<T> implements Observer<T>{
        private Observer observer;
        private boolean shouldConsumeFirstNotify;
        public EasyObserver(Observer observer) {
            this.observer = observer;
            shouldConsumeFirstNotify = isNewLiveData(EasyLiveData.this);
        }

        @Override
        public void onChanged(T t) {
            //第一次进来，没有发生过数据变更，则后续的变更直接通知。
            if (shouldConsumeFirstNotify) {
                observer.onChanged(t);
            } else {
                //若是LiveData 之前就有数据变更，那么这一次的变更不处理
                shouldConsumeFirstNotify = true;
            }
        }

        private boolean isNewLiveData(LiveData liveData) {
            Class ldClass = LiveData.class;
            try {
                Method method = ldClass.getDeclaredMethod("getVersion");
                method.setAccessible(true);
                //获取版本
                int version = (int)method.invoke(liveData);
                //版本为-1，说明是初始状态，LiveData 还未发生过数据变更。
                return version == -1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
