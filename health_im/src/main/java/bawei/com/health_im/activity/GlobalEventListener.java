package bawei.com.health_im.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */

/*

 *作者:赵星海

 *时间:18/11/21 16:08

 *用途:极光IM消息接收处理

 */


/*

 *作者:赵星海

 *时间:18/11/21 16:08

 *用途:极光IM消息接收处理

 */

public class GlobalEventListener {



    private Context MainContext;





    private static Activity_img JG_details = null;// 会话详情对象



    public GlobalEventListener(Context context) {

        MainContext = context;

        JMessageClient.registerEventReceiver(this);

    }

    public static void setJG(Activity activity, boolean islist) {


           JG_details = (Activity_img) activity;

    }

    //通知点击 前往会话列表

    public void onEvent(NotificationClickEvent event) {
        MainContext.startActivity(new Intent(MainContext, Activity_img.class));
    }
    // 接收消息 (主线程)(刷新UI)
    public void onEventMainThread(MessageEvent event){
        if (JG_details != null) {
            JG_details.initData();
        }else {
            JG_details.initData();
        }
    }
}
