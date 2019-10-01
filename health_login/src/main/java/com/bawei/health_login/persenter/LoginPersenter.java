package com.bawei.health_login.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class LoginPersenter extends WDPresenter<IAppRequest> {


    public LoginPersenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.login((String) args[0],(String)args[1]);
    }
}
