package bawei.com.health_im.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class LishiPersenter extends WDPresenter<IAppRequest> {
    public LishiPersenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.message((Long) args[0],(String) args[1],(int) args[2],(int)args[3],(int)args[4]);
    }
}
