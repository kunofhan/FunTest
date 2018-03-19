package check.power.cdd.com.testphone;

import android.app.Service;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.HashSet;

public class NaoLingActivity extends AppCompatActivity {

    private MediaPlayer player;
    private Button ring;
    //    private RingtoneManager ringtoneManager;
//    private HashSet<String> listRong;
    private MediaPlayer mMediaPlayer;
    private Uri URL;
    private Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nao_ling);


        URL = RingtoneManager.getActualDefaultRingtoneUri(this,
                RingtoneManager.TYPE_RINGTONE);
        mVibrator=(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
/*
//        Log.d(TAG, "initView: " + getSystemDefultRingtoneUri());//获取系统的铃声
        ringtoneManager = new RingtoneManager(this); // 铃声管理器
        Cursor cursor = ringtoneManager.getCursor(); //获取铃声表,根据表名取值
        while (cursor != null && cursor.moveToNext()) {
            listRong.add(cursor.getString(cursor.getColumnIndex("title")));
            listIds.add(cursor.getInt(cursor.getColumnIndex("_id")));
        }
*/
        ring = findViewById(R.id.notify_ring);
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //系统提示音
                startAlarm(NaoLingActivity.this);
                //闹钟铃声
                startAlarm(URL);
//                if (mVibrator.)
                //震动：分别表示等待1000ms 震动500ms 再等待1000ms 震动500ms repeat = 0代表循环播放
                mVibrator.vibrate(new long[]{1000,500,1000,500,1000},0);
            }
        });
    }

    private void startAlarm(Uri uri) {
        if (mMediaPlayer == null) {
            //获取音乐播放器
            mMediaPlayer = MediaPlayer.create(this, uri);
            mMediaPlayer.setLooping(false);
            try {
                mMediaPlayer.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaPlayer.start();
        } else {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            } else {
                mMediaPlayer.start();
            }
        }
        /*if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        } else {
            if (mMediaPlayer == null) {

            }
            try {
                mMediaPlayer.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaPlayer.start();
        }*/
    }

    private void startAlarm(Context context) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (notification == null) return;
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
    }
}
