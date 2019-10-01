package bawei.com.health_im.util;

import android.media.MediaRecorder;

import java.io.File;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class MyMediaRecorder {
    private static MyMediaRecorder instance;

    private MediaRecorder mMediaRecorder;

    private String filePath;

    private MyMediaRecorder() {

    }

    public static synchronized MyMediaRecorder getInstance() {
        if (null == instance) {
            synchronized (MyMediaRecorder.class) {
                if (null == instance) {
                    instance = new MyMediaRecorder();
                }
            }
        }
        return instance;
    }
    public void startRecord(String path) {
        if (null == mMediaRecorder) {
            mMediaRecorder = new MediaRecorder();
        }
        try {
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            filePath = path;
            mMediaRecorder.setOutputFile(filePath);
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopRecord() {
        try {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
            filePath = "";
        } catch (RuntimeException e) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            filePath = "";
        }
    }

}
