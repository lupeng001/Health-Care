package bawei.com.health_main.card;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

import bawei.com.health_main.R;

public class OtherSideActivity extends Activity implements View.OnClickListener {

    private EditText et_office;
    private ImageView iv_mg;
    private RelativeLayout rl_bk_back;
    private Context context;
    private SharedPreferences sp;
    private EditText et_lssue_data, et_valid_until;
    private Activity activity;
    private Button iv_id_b_sure;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_side);

        context = this;
        activity = this;
        sp = PreferenceManager.getDefaultSharedPreferences(context);

        initView();

        Bundle extras = getIntent().getBundleExtra("bundle");

        if (extras != null) {
            String issued_by = extras.getString("issued_by");
            String valid_date = extras.getString("valid_date");
            path = extras.getString("img_path");
            int direction = extras.getInt("direction");

            et_office.setText(issued_by);
            DisplayUtil.showSoftInputFromWindow(activity, et_office);

            String[] spit = valid_date.split("-");
            et_lssue_data.setText(spit[0]);
            et_valid_until.setText(spit[1]);


            if (!TextUtils.isEmpty(path)) {
                try {

                    File file = new File(path);
                    FileInputStream inStream = null;

                    inStream = new FileInputStream(file);
                    Bitmap bitmap = BitmapFactory.decodeStream(inStream);

                    if (direction == 1) {
                        //逆时针90度
                        bitmap = UrlUtils.rotatePicture(bitmap, 90);
                    } else if (direction == 2) {
                        //逆时针180度
                        bitmap = UrlUtils.rotatePicture(bitmap, 180);
                    } else if (direction == 3) {
                        //逆时针270度
                        bitmap = UrlUtils.rotatePicture(bitmap, 270);
                    }

                    iv_mg.setImageBitmap(bitmap);
                    inStream.close();

                    boolean b = UrlUtils.saveBmpToPath(bitmap, path);
                    Log.v("PhotoGraphActivity", direction + "," + b);

                    sp.edit().putString("id_card_back", path).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initView() {
        iv_id_b_sure =  findViewById(R.id.iv_id_b_sure);
        rl_bk_back = (RelativeLayout) findViewById(R.id.rl_bk_back);
        et_office = (EditText) findViewById(R.id.et_office);
        et_lssue_data = (EditText) findViewById(R.id.et_lssue_data);
        et_valid_until =  findViewById(R.id.et_valid_until);
        iv_mg = (ImageView) findViewById(R.id.iv_mg);
        rl_bk_back.setOnClickListener(this);
        iv_id_b_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.rl_bk_back) {
            finish();

        } else if (i == R.id.iv_id_b_sure) {
            Toast.makeText(context, "信息保存成功!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("com.from.call.back.id.card.back");
            intent.putExtra("sign_orgin", et_office.getText().toString());
            intent.putExtra("expiration_date", et_lssue_data.getText().toString() + "-" +
                    et_valid_until.getText().toString());
            intent.putExtra("paths",path);
            sendBroadcast(intent);
            finish();

        } else {
        }
    }
}
