package clear.back2;

import java.io.ByteArrayOutputStream;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

//main activity
public class ClearBack2Activity extends Activity implements SensorEventListener {
	protected static final String TAG = "MAIN_ACTIVITY";
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT; 
	public static int DYSPLAY_SIZE_W = 0;
	public static int DYSPLAY_SIZE_H = 0;
	public static int POINT_X;
    public static int POINT_Y;
    public static int pass = 0;
	private final int REPEAT_INTERVAL = 50;
    private Handler handler = new Handler();
    private Runnable runnable;
    private SensorManager manager;
    
    private int Y = 0;
   
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		displaysize();
		final CameraView ccd = new CameraView(this);
		manager = (SensorManager)getSystemService(SENSOR_SERVICE);
		//カメラ画面
		LinearLayout l = new LinearLayout(this);
		l.addView(ccd,createParam(DYSPLAY_SIZE_W, DYSPLAY_SIZE_H));

		//ボタン画面
		LinearLayout h = new LinearLayout(this);
		// h.setOrientation(LinearLayout.VERTICAL);
		//l.addView(h);
		setContentView(l);
		
		
		final overlay overlay = new overlay(this);
		
		addContentView(overlay, new LayoutParams(LayoutParams.FILL_PARENT,
		        LayoutParams.FILL_PARENT));
		
		
		
		
		
		ImageButton imgbutton1 = new ImageButton(this);
        imgbutton1.setImageResource(R.drawable.ic_launcher);
        imgbutton1.setPadding(WC, WC, WC, WC);
        h.addView(imgbutton1, createParam(WC, WC));
        imgbutton1 .setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ccd.satuei();
			}});
		
        
        
        
       // Intent i = new Intent(ClearBack2Activity.this,camera.class);
        //i.putExtra("CAMERA", ccd.satuei());
        //startActivity(i);
        
      
		
		/*button = new Button(this);
		//button.setText("撮影");
		// 
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ccd.satuei();
			}});
		h.addView(button, createParam(WC, WC));*/
        runnable = new Runnable() {
            @Override
            public void run() {

                //2.繰り返し処
            	//Log.d(TAG, "********onDrowivent       "+Y+"    ****************");
            	if(70<Y){
            		pass = 90;
            		overlay.kaiten(pass);
            	}
            	if(Y<-70){
            		pass = 270;
            		overlay.kaiten(pass);
            	}
            	
            	if(Y==0){
            		pass = 0;
            		overlay.kaiten(pass);
            	}
                //3.次回処理をセット
                handler.postDelayed(this, REPEAT_INTERVAL);
            }
        };
        
      //1.初回実行
        handler.postDelayed(runnable, REPEAT_INTERVAL);
	}
	
	
	
	
	
	
	
	
	private LinearLayout.LayoutParams createParam(int width, int height){
		return new LinearLayout.LayoutParams(width, height);
	}   
	//画面サイズ取得
	public void displaysize(){
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DYSPLAY_SIZE_W = display.getWidth();
		DYSPLAY_SIZE_H = display.getHeight();
		Log.d("display", "w:" + display.getWidth());
		Log.d("display", "h:" + display.getHeight());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	//カメラ内部class
	public class CameraView extends SurfaceView implements Callback ,PictureCallback {
		public final Bitmap bmp =null;
		static final int count = 0;
		public   Camera camera = null;
		//ファイルの保存先フォルダ
		final String ROOT_PATH =
				Environment.getExternalStorageDirectory() +
				File.separator +
				getResources().getString(R.string.app_name) +
				File.separator;

		public CameraView(Context context) {
			super(context);
			SurfaceHolder holder = getHolder();
			holder.addCallback(this);
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}

		//surface起動時の処理
		public void surfaceCreated(SurfaceHolder holder) {
			try {
				camera = Camera.open();
				camera.setPreviewDisplay(holder);
			} catch(IOException e) {
			}
			//SDカードに保存可能かの確認
			final File f = new File(ROOT_PATH);
			if( !f.exists() ){
				f.mkdir();
				Toast.makeText(
						getContext(),
						"SD card ok. camera start.",
						Toast.LENGTH_SHORT).show();}
			else{
				Toast.makeText(
						getContext(),
						"SD card can not write.",
						Toast.LENGTH_SHORT).show();
			}
		}

		public void surfaceChanged(SurfaceHolder holder, int f, int w, int h) {
			Camera.Parameters p = camera.getParameters();
			//p.setPreviewSize(w,h);
			camera.setParameters(p);
			camera.startPreview();
		}

		//surface終了時の処理
		public void surfaceDestroyed(SurfaceHolder holder) {
			camera.setPreviewCallback(null); 
			camera.stopPreview();
			camera.release();
			camera = null; 
		}

		//撮影処理後の画像の向きの修正と保存
		public void onPictureTaken(byte[] data, Camera camera) {

			Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length,null);	
			//Bitmap b = bmp.copy(Bitmap.Config.ARGB_8888, true); 
			Matrix matrix = new Matrix();
			matrix.postRotate(pass+90);
			int width = bmp.getWidth();
			int height = bmp.getHeight();
			Bitmap bmp2 = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, false); 
			Log.d(null, "onPictureTaken");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bmp2.compress(CompressFormat.JPEG, 100, bos);
			// bos.toByteArray() で byte[] が取れる。
			final String path=
					//時間でファイル名の指定
					//ROOT_PATH + dateFormat.format(new Date()) + ".jpg";

					//ダイレクトに指定
					ROOT_PATH + "clearback" + ".jpg";

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(path);
			} catch (FileNotFoundException e1) {
				//Log.d("MyCameraView", e1.getMessage() );
			}

			if( fos != null){
				try {
					fos.write(bos.toByteArray());

					Log.d(null, "onPictureTaken成功");
				} catch (IOException e1) {
					Log.d(null, "onPictureTaken失敗");
					e1.printStackTrace();
				}

				try {
					fos.close();
					fos = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			 
			Intent i = new Intent(getApplicationContext(),SubActivity.class);
			startActivity(i);
			Intent l = new Intent(getApplicationContext(),sub2activity.class);
			startActivity(l);
			//camera.startPreview();
		}
		
		//プレビュー画面をタッチしたときの動作
		//ハードの決定ボタン含みます
		@Override
		public boolean onTouchEvent(MotionEvent me) {
			if(10<=me.getX() && me.getX()<=200 && 10<=me.getY() && me.getY()<=200 && me.getAction()==MotionEvent.ACTION_DOWN) {
				//autoFocus();
			}
				else if(DYSPLAY_SIZE_W-POINT_X<=me.getX() && me.getX()<=DYSPLAY_SIZE_W &&
						(DYSPLAY_SIZE_H/2)-(POINT_Y/2)<=me.getY() && me.getY()<= (DYSPLAY_SIZE_H/2)+(POINT_Y/2) && me.getAction()==MotionEvent.ACTION_DOWN){
					//autoFocus();
					camera.takePicture(null,null,this);
			}
			
			return true;
		}

		//main activityでの撮影ボタンの動作
		//基本的にonTouchEventと同じです
		public boolean satuei() {
			//autoFocus();
			camera.takePicture(null,null,this);
			return true;
		}

		//オートフォーカス
		public void autoFocus(){
			if( camera != null ){
				camera.autoFocus( new Camera.AutoFocusCallback() {
					public void onAutoFocus( boolean success, Camera camera ){
						camera.autoFocus( null );	

					}
				} );
			}
		}
	}














	
	@Override
	protected void onStop() {
	 // TODO Auto-generated method stub
	 super.onStop();
	 // Listenerの登録解除
	 manager.unregisterListener(this);;
	 }

	 @Override
	 protected void onResume() {
	// TODO Auto-generated method stub
	 super.onResume();
	 // Listenerの登録
	 List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ORIENTATION);
	 if(sensors.size() > 0) {
	 Sensor s = sensors.get(0);
	 manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
	 }
	 }








	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO 自動生成されたメソッド・スタブ
		
	}








	@Override
	public void onSensorChanged(SensorEvent event) {
		int senser = (int) event.values[1];
		if(event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			Y = (int) event.values[2];
			
	
			}
		}
	
	}




