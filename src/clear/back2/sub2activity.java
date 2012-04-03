package clear.back2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Context;
import android.graphics.Color;

public class sub2activity extends Activity {
	static Button button;
	  private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
	  private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	  private int MAX_TEXT_TWITTER = 140;
	  
	  
	    @Override
	    protected void onCreate(Bundle icicle) {
	        super.onCreate(icicle);
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
	        LinearLayout l = new LinearLayout(this);
	        l.setOrientation(LinearLayout.VERTICAL);
	        //��̗]���쐬�̂��߁A������TextView���쐬
	        //�]���쐬�̂��߂����Ȃ̂ŃV�X�e���ɂ͊֌W����܂���
	        TextView tv = new TextView(this);
	        tv.setText("");
	        tv.setWidth(80);
	        tv.setHeight(40);
	        tv.setBackgroundColor(Color.TRANSPARENT);
	        l.addView(tv,  new LinearLayout.LayoutParams(ClearBack2Activity.DYSPLAY_SIZE_H, ClearBack2Activity.DYSPLAY_SIZE_W/2));
	        
	        //�\�������e�L�X�g���͗�
	        final EditText edit1 = new EditText(this);
	        edit1.setWidth(ClearBack2Activity.DYSPLAY_SIZE_H);
	        edit1.setHeight((ClearBack2Activity.DYSPLAY_SIZE_W/2)-100);
	        l.addView(edit1, 
	          new LinearLayout.LayoutParams(ClearBack2Activity.DYSPLAY_SIZE_H, (ClearBack2Activity.DYSPLAY_SIZE_W/2)-100));
	       
			//�A�b�v���[�h�{�^��
			LinearLayout h = new LinearLayout(this);
			// h.setOrientation(LinearLayout.VERTICAL);
			l.addView(h);
			setContentView(l);

			button = new Button(this);
			button.setText("�A�b�v���[�h");
			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					String text = edit1.getText().toString();
					MAX_TEXT_TWITTER = MAX_TEXT_TWITTER - text.length();
					Log.d(null, text);
				}});
			h.addView(button, createParam(WC, WC));
		}

	       
		private LinearLayout.LayoutParams createParam(int width, int height){
			return new LinearLayout.LayoutParams(width, height);
		}   
		//��ʃT�C�Y�w��
		}
