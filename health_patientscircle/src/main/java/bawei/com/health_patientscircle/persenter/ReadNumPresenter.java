package bawei.com.health_patientscircle.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class ReadNumPresenter extends WDPresenter<IAppRequest> {
    public ReadNumPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getReadNum((String)args[0],(String)args[1]);
    }
}
