package com.bawei.health_login.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class RuzhuPersenter extends WDPresenter<IAppRequest> {
    public RuzhuPersenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.ruzhu((RequestBody) args[0]);
    }
}
