package bawei.com.health_patientscircle.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;
import com.dingtao.common.core.http.IBaiduRequest;

import io.reactivex.Observable;

public class FindDepartmentPresenter extends WDPresenter<IAppRequest> {
    public FindDepartmentPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getDepartment();
    }
}
