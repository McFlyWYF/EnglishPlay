package com.example.englishplay.seeactivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.example.englishplay.R;
import com.example.englishplay.readactivity.tools.SystemBarTintManager;

import java.io.File;
import java.io.IOException;

/*
what to see activity下方两个按钮的点击事件
 */
public class SeeActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback{

    private String imageUri;
    private ImageView picture;
    private ImageView most,next;


    private static final String TAG = "CameraActivity1";
    private SurfaceView camera_sf;
    //private Button camera_btn;
    private Camera mCamera;
    private SurfaceHolder mHolder;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);

        most = findViewById(R.id.most_like);
        //next = findViewById(R.id.next_like);

        most.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeActivity.this,TranslateActivity.class);
                startActivity(intent);
            }
        });

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SeeActivity.this,TranslateActivity.class);
//                startActivity(intent);
//            }
//        });

        changeSystemBar();
        initViews();

    }

    private void initViews() {
        this.camera_sf = findViewById(R.id.surfaceView);
        camera_sf.setOnClickListener(this);
        mHolder = camera_sf.getHolder();
        mHolder.addCallback(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (null == mCamera) {
            mCamera = getCustomCamera();
            if (mHolder != null) {
                previceCamera(mCamera, mHolder);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }


    private void changeSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //系统版本大于19
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        //tintManager.setStatusBarTintResource(R.drawable.toolbar);//设置标题栏颜色，此颜色在color中声明
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;        // a|=b的意思就是把a和b按位或然后赋值给a   按位或的意思就是先把a和b都换成2进制，然后用或操作，相当于a=a|b
        } else {
            winParams.flags &= ~bits;        //&是位运算里面，与运算  a&=b相当于 a = a&b  ~非运算符
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onClick(View v) {

    }

//    private void startTakephoto() {
//
//        //获取到相机参数
//        Camera.Parameters parameters = mCamera.getParameters();
////       //设置图片保存格式
////        parameters.setPictureFormat(ImageFormat.JPEG);
//        //设置图片大小
//        parameters.setPreviewSize(getWidth(), getHeight());
//        parameters.setPictureSize(getWidth(),getHeight());
//        mCamera.setDisplayOrientation(90);
//        mCamera.setParameters(parameters);
//        //设置对焦
//        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
//        //设置自动对焦
//        mCamera.autoFocus(new Camera.AutoFocusCallback() {
//            @Override
//            public void onAutoFocus(boolean success, Camera camera) {
//                if (success) {
//                    mCamera.takePicture(null, null, new Camera.PictureCallback() {
//                        @Override
//                        public void onPictureTaken(byte[] data, Camera camera) {
//                            //dealWithCameraData(data);
//                        }
//                    });
//                }
//            }
//        });
//    }

//    //保存拍照数据
//    private void dealWithCameraData(byte[] data) {
//        FileOutputStream fos = null;
//        String tempStr = "/sdcard/ldm/";
//        //图片临时保存位置
//        String fileName = tempStr + System.currentTimeMillis() + ".jpg";
//        File tempFile = new File(fileName);
//        try {
//            fos = new FileOutputStream(fileName);
//            //保存图片数据
//            fos.write(data);
//            fos.close();
//            Intent intent = new Intent(CameraActivity1.this, ShowResultActivity.class);
//            intent.putExtra(FILE_PATH, fileName);
//            startActivity(intent);
//            finish();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        previceCamera(mCamera, holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview();
        previceCamera(mCamera, holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);
            //停止预览
            mCamera.stopPreview();
            //释放相机资源
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera getCustomCamera() {
        if (null == mCamera) {
            //使用Camera的Open函数开机摄像头硬件
            mCamera = Camera.open();
            //Camera.open()方法说明：2.3以后支持多摄像头，所以开启前可以通过getNumberOfCameras先获取摄像头数目，
            // 再通过 getCameraInfo得到需要开启的摄像头id，然后传入Open函数开启摄像头，
            // 假如摄像头开启成功则返回一个Camera对象
        }
        return mCamera;
    }

    private void previceCamera(Camera camera, SurfaceHolder holder) {
        try {
            //摄像头设置SurfaceHolder对象，把摄像头与SurfaceHolder进行绑定
            camera.setPreviewDisplay(holder);
            //调整系统相机拍照角度
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
