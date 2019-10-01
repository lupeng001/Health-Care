package bawei.com.health_patientscircle.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;


import io.reactivex.Observable;

public class PublishPresenter extends WDPresenter<IAppRequest> {
    public PublishPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getPublish((Long) args[0],(String)args[1],(int)args[2],(String)args[3]);
    }
}
