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
    int width;
    int height;
    public overlay(Context context) {
        super(context);
        Resources r = context.getResources();
        image = BitmapFactory.decodeResource(r, R.drawable.ic_launcher2);
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
        int w = image.getWidth();
        int h = image.getHeight();
        Rect src = new Rect(0, 0, w, h);
        canvas.drawBitmap(image, ClearBack2Activity.DYSPLAY_SIZE_W-100,0, paint);
        
        
        
        paint.setColor(Color.argb(255, 255, 0, 0));
	        paint.setStyle(Style.STROKE);
	        paint.setStrokeWidth(3.0f);
        //位置を調整しています。x,y軸を調整することで、表示位置を変更できます。
	        canvas.drawRect(10,10,200,200,
 					paint);
	        
    }
    
   public void kaiten(){
	   Log.d(TAG, "********onDrowivent                      ****************");
	   Resources r = getResources();
	    image = BitmapFactory.decodeResource(r, R.drawable.ic_launcher);
	   invalidate();
   }
   
   public void kaiten2(){
	   Resources r = getResources();
	    image = BitmapFactory.decodeResource(r, R.drawable.ic_launcher2);
	   invalidate();

   }
}