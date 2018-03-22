package com.comsoftstar.autobicycle.Model.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.comsoftstar.autobicycle.BR;

/**
 * Created by Administrator on 2017/10/10.
 */

public class Fragment1_Item extends BaseObservable{
    private String length;
    private String electric;
    private String speed;
    private String module;
    public Fragment1_Item(String length, String electric, String speed, String module) {
        this.length = length;
        this.electric = electric;
        this.speed = speed;
        this.module = module;
    }
    @Bindable
    public String getModule() {
        return module;
    }
    @Bindable
    public String getLength() {
        return length;
    }
    @Bindable
    public String getElectric() {
        return electric;
    }
    @Bindable
    public String getSpeed() {
        return speed;
    }

    public void setLength(String length) {
        this.length = length;
        notifyPropertyChanged(BR.length);
    }
    public void setModule(String module) {
        this.module = module;
        notifyPropertyChanged(BR.module);
    }
    public void setElectric(String electric) {
        this.electric = electric;
        notifyPropertyChanged(BR.electric);
    }
    public void setSpeed(String speed) {
        this.speed = speed;
        notifyPropertyChanged(BR.speed);
    }
}
