package clear.back2;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class overlay extends View {
	
	
	
	private static final String TAG = null;
	private Bitmap image;
	private Bitmap image2;
    int width;
    int height;
    
    public overlay(Context context) {
        super(context);
        Resources r = context.getResources();
        image = BitmapFactory.decodeResource(r, R.drawable.ic_launcher);
        Matrix matrix = new Matrix();
        matrix.postRotate(270);
        matrix.postScale(2.0f, 2.0f);
		int width = image.getWidth();
		int height = image.getHeight();
		image = Bitmap.createBitmap(image, 0, 0, width, height, matrix, false); 
		image2 = image;
		ClearBack2Activity.POINT_X = image.getWidth();
		ClearBack2Activity.POINT_Y = image.getHeight();
        setDrawingCacheEnabled(true);  
        
        
    }
    
   
    
    
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        //ビューのサイズを取得
        width= w;
        height= h;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        canvas.drawColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        int w = image2.getWidth();
        int h = image2.getHeight();
        Rect src = new Rect(0, 0, w, h);
        canvas.drawBitmap(image2, ClearBack2Activity.DYSPLAY_SIZE_W-image.getWidth(),(ClearBack2Activity.DYSPLAY_SIZE_H/2)-(image.getHeight()/2), paint);
        
        
        
        paint.setColor(Color.argb(255, 255, 0, 0));
	        paint.setStyle(Style.STROKE);
	        paint.setStrokeWidth(3.0f);
        //位置を調整しています。x,y軸を調整することで、表示位置を変更できます。
	        canvas.drawRect(10,10,200,200,
 					paint);
	        
    }
    
   public void kaiten(int ichi){
	   
		   Matrix matrix = new Matrix();
			matrix.postRotate(ichi);
			int width = image.getWidth();
			int height = image.getHeight();
			image2 = Bitmap.createBitmap(image, 0, 0, width, height, matrix, false); 
	                 
	        
	   invalidate();
   }
   
   
}