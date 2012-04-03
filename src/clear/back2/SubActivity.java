package clear.back2;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;


//背景画像の表示
public class SubActivity extends Activity  {
	private static final int MENU_ID_MENU1 = (Menu.FIRST + 1);
    private static final int MENU_ID_MENU2 = (Menu.FIRST + 2);
    private boolean visible = true;
    private static final String TAG = "menu";
    public static int WI;
    public static int HI;
    
	 /** 画面描画用 View */
    MyCircleView view;
    /** Timer 処理用のハンドラ */
    android.os.Handler handler = new android.os.Handler();
    /** Activityが生成された時に呼ばれる */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        // View の設定
        view = new MyCircleView(getApplication());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);    
        /*overlay verlay = new overlay(this);
        addContentView(verlay, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));*/
        
        
        
    }
    
}



    class MyCircleView extends View {
    	private static final String TAG = "view";
    	bitsyori abc = new bitsyori();
    	public MyCircleView(Context context) {
    		super(context);
    		 
    		// TODO 自動生成されたコンストラクター・スタブ
    	}
    	
    	 protected void onDraw(Canvas canvas) {
    		 super.onDraw(canvas);
    		 
 	        
    		 //Log.d(TAG, "*******************      "+ abc.nn() +"           *************************************************");
    		 
    		 
 	    	//Bitmap imageCopy = abc.bitmap();
 	    	// canvas = new Canvas(imageCopy);
 	        Paint paint = new Paint();
 	       canvas.drawBitmap(abc.bitmap(),0,0,paint);
 	        //Log.d(TAG, "********onDrowivent**********"+num+"******");
 	       
    	        }  
    	 
	    
    
    }
    
    class bitsyori{
    	private static final int MAX_FACES = 200;
    	static float[] facenx = new float[MAX_FACES]; 
    	static float[] faceny = new float[MAX_FACES]; 
    	static int num;
    	static float[] ryoume = new float[MAX_FACES];
    	static Canvas ccc;
    
    	
    	
    	
    	
    	
    	 public Bitmap bitmap(){
    		// String filePath = Environment.getExternalStorageDirectory().getPath() + "/sample.jpg";	
    		 final String filePath =
    					Environment.getExternalStorageDirectory() +
    					File.separator +
    					"ClearBack2" +
    					File.separator + "clearback" + ".jpg";
    		 //IMAG0207.jpg
         	/*Bitmap image = BitmapFactory.decodeFile(filePath);
         	Bitmap imageCopy = image.copy(Config.ARGB_8888, true);
         	
    		 
    		//読み込み用のオプションオブジェクトを生成
    		 BitmapFactory.Options options = new BitmapFactory.Options();
    		 //この値をtrueにすると実際には画像を読み込まず、
    		 //画像のサイズ情報だけを取得することができます。
    		 options.inJustDecodeBounds = true;

    		 //画像ファイル読み込み
    		 //ここでは上記のオプションがtrueのため実際の
    		 //画像は読み込まれないです。
    		 BitmapFactory.decodeFile(filePath, options);

    		 //読み込んだサイズはoptions.outWidthとoptions.outHeightに
    		 //格納されるので、その値から読み込む際の縮尺を計算します。
    		 //このサンプルではどんな大きさの画像でもHVGAに収まるサイズを
    		 //計算しています。
    		 //int scaleW = options.outWidth / 2;
    		 //int scaleH = options.outHeight /2;

    		 //縮尺は整数値で、2なら画像の縦横のピクセル数を1/2にしたサイズ。
    		 //3なら1/3にしたサイズで読み込まれます。
    		 int scale = 1; 
    		 //Math.max(scaleW, scaleH);

    		 //今度は画像を読み込みたいのでfalseを指定
    		 options.inJustDecodeBounds = false;

    		 //先程計算した縮尺値を指定A
    		 options.inSampleSize = scale;
    		 //options.inDither = true;
    		 //これで指定した縮尺で画像を読み込めます。
    		 //もちろん容量も小さくなるので扱いやすいです。*/
    		 BitmapFactory.Options options = new BitmapFactory.Options();
    		 Bitmap image = BitmapFactory.decodeFile(filePath, options);
    		 image = Bitmap.createScaledBitmap(image,  ClearBack2Activity.DYSPLAY_SIZE_H, ClearBack2Activity.DYSPLAY_SIZE_W, true);
    		 //Bitmap imageCopy = image.copy(Config.ARGB_8888, true);
    		 //options.inPurgeable = true;
         	//return pictdeta.pictout();
    		 return image;
         }
    	 
    	 
 	   }
    
 	   
    
    
        

    
    
   
