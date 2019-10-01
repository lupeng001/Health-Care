package bawei.com.health_patientscircle.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class AllStatusPresenter extends WDPresenter<IAppRequest> {
    public AllStatusPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getStatus((String)args[0],(String)args[1]);
    }
}
