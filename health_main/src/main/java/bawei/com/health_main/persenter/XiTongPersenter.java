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
public class XiTongPersenter extends WDPresenter<IAppRequest> {
    public XiTongPersenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.xitong((Long)args[0],(String)args[1]);
    }
}
