package bawei.com.health_im.persenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class PushVoiceMsgPersenter extends WDPresenter<IAppRequest> {
    public PushVoiceMsgPersenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.uploadFile((Long) args[0],(String)args[1],(HashMap)args[2],(MultipartBody.Part)args[3]);
    }
}
