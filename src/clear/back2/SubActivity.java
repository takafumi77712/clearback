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


//�w�i�摜�̕\��
public class SubActivity extends Activity  {
	private static final int MENU_ID_MENU1 = (Menu.FIRST + 1);
    private static final int MENU_ID_MENU2 = (Menu.FIRST + 2);
    private boolean visible = true;
    private static final String TAG = "menu";
    public static int WI;
    public static int HI;
    
	 /** ��ʕ`��p View */
    MyCircleView view;
    /** Timer �����p�̃n���h�� */
    android.os.Handler handler = new android.os.Handler();
    /** Activity���������ꂽ���ɌĂ΂�� */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        // View �̐ݒ�
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
    		 
    		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
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
         	
    		 
    		//�ǂݍ��ݗp�̃I�v�V�����I�u�W�F�N�g�𐶐�
    		 BitmapFactory.Options options = new BitmapFactory.Options();
    		 //���̒l��true�ɂ���Ǝ��ۂɂ͉摜��ǂݍ��܂��A
    		 //�摜�̃T�C�Y��񂾂����擾���邱�Ƃ��ł��܂��B
    		 options.inJustDecodeBounds = true;

    		 //�摜�t�@�C���ǂݍ���
    		 //�����ł͏�L�̃I�v�V������true�̂��ߎ��ۂ�
    		 //�摜�͓ǂݍ��܂�Ȃ��ł��B
    		 BitmapFactory.decodeFile(filePath, options);

    		 //�ǂݍ��񂾃T�C�Y��options.outWidth��options.outHeight��
    		 //�i�[�����̂ŁA���̒l����ǂݍ��ލۂ̏k�ڂ��v�Z���܂��B
    		 //���̃T���v���ł͂ǂ�ȑ傫���̉摜�ł�HVGA�Ɏ��܂�T�C�Y��
    		 //�v�Z���Ă��܂��B
    		 //int scaleW = options.outWidth / 2;
    		 //int scaleH = options.outHeight /2;

    		 //�k�ڂ͐����l�ŁA2�Ȃ�摜�̏c���̃s�N�Z������1/2�ɂ����T�C�Y�B
    		 //3�Ȃ�1/3�ɂ����T�C�Y�œǂݍ��܂�܂��B
    		 int scale = 1; 
    		 //Math.max(scaleW, scaleH);

    		 //���x�͉摜��ǂݍ��݂����̂�false���w��
    		 options.inJustDecodeBounds = false;

    		 //����v�Z�����k�ڒl���w��A
    		 options.inSampleSize = scale;
    		 //options.inDither = true;
    		 //����Ŏw�肵���k�ڂŉ摜��ǂݍ��߂܂��B
    		 //�������e�ʂ��������Ȃ�̂ň����₷���ł��B*/
    		 BitmapFactory.Options options = new BitmapFactory.Options();
    		 Bitmap image = BitmapFactory.decodeFile(filePath, options);
    		 image = Bitmap.createScaledBitmap(image,  ClearBack2Activity.DYSPLAY_SIZE_H, ClearBack2Activity.DYSPLAY_SIZE_W, true);
    		 //Bitmap imageCopy = image.copy(Config.ARGB_8888, true);
    		 //options.inPurgeable = true;
         	//return pictdeta.pictout();
    		 return image;
         }
    	 
    	 
 	   }
    
 	   
    
    
        

    
    
   
