package bawei.com.health_main.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class DoctorPushTokenPersenter extends WDPresenter<IAppRequest> {
    public DoctorPushTokenPersenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.DoctorPushToken((Long) args[0],(String) args[1],(String)args[2]);
    }
}
