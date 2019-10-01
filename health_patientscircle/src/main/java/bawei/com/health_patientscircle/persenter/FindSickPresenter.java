package bawei.com.health_patientscircle.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class FindSickPresenter extends WDPresenter<IAppRequest> {
    public FindSickPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getFindSick((int)args[0],(int)args[1],(int)args[2]);
    }
}
