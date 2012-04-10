package clear.back2;

import java.io.File;


import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;

public class sub2activity extends Activity {
	static Button button;
	final String filePath =
			Environment.getExternalStorageDirectory() +
			File.separator +
			"ClearBack2" +
			File.separator + "clearback" + ".jpg";
	SharedPreferences pref;
	
    SharedPreferences.Editor editor;
	  private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
	  private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	  private int MAX_TEXT_TWITTER = 140;
	  private static final String CALLBACK = "https://www.google.co.jp/";
	protected static final String TAG = null;
	  String consumerKey = "tgVo7M2aZtlvsoXkYK25uQ";
	  String consumerSecret = "	Ni4WueS2BSl5lso2EV78zoUgDjVF9gFk4Y7CiZKQ4j8";
		String oAuthAccessToken = "549775270-4ZNEqzXDZGH97lJxowoHjbEMpILDH4Eqhgr1aA7O";
	     String oAuthAccessTokenSecret = "bCkt9XBx8xesIftaFoJjn73NkztHgfos1B7j6AQWGg";
	     private OAuthAuthorization mOauth;
	     private Configuration mConf;
	    @Override
	    protected void onCreate(Bundle icicle) {
	        super.onCreate(icicle);
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
	        LinearLayout l = new LinearLayout(this);
	        l.setOrientation(LinearLayout.VERTICAL);
	        //上の余白作成のため、透明なTextViewを作成
	        //余白作成のためだけなのでシステムには関係ありません
	        TextView tv = new TextView(this);
	        tv.setText("");
	        tv.setWidth(80);
	        tv.setHeight(40);
	        tv.setBackgroundColor(Color.TRANSPARENT);
	        l.addView(tv,  new LinearLayout.LayoutParams(ClearBack2Activity.DYSPLAY_SIZE_H, ClearBack2Activity.DYSPLAY_SIZE_W/2));
	        
	        //表示されるテキスト入力欄
	        final EditText edit1 = new EditText(this);
	        edit1.setWidth(ClearBack2Activity.DYSPLAY_SIZE_H);
	        edit1.setHeight((ClearBack2Activity.DYSPLAY_SIZE_W/2)-100);
	        l.addView(edit1, 
	          new LinearLayout.LayoutParams(ClearBack2Activity.DYSPLAY_SIZE_H, (ClearBack2Activity.DYSPLAY_SIZE_W/2)-100));
	       
			//アップロードボタン
			LinearLayout h = new LinearLayout(this);
			// h.setOrientation(LinearLayout.VERTICAL);
			l.addView(h);
			setContentView(l);			
			
			button = new Button(this);
			button.setText("アップロード");
			button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					String text = edit1.getText().toString();
				
					
					
					
					
					
					
					MAX_TEXT_TWITTER = MAX_TEXT_TWITTER - text.length();
					Log.d(null, text);
					try {
						tweet(text);
					} catch (TwitterException e) {
						Log.d(null, "cccccccccccccccccccccccccccccccccccccccccccccccccc");

						e.printStackTrace();
					}
				}});
			h.addView(button, createParam(WC, WC));
		}

	       
		private LinearLayout.LayoutParams createParam(int width, int height){
			return new LinearLayout.LayoutParams(width, height);
		}   
		
		public void tweet(String text) throws TwitterException {
			Twitter mTwitter = new TwitterFactory().getInstance();
            mTwitter.setOAuthConsumer(consumerKey, consumerSecret);
            mTwitter.setOAuthAccessToken(new AccessToken(oAuthAccessToken, oAuthAccessTokenSecret));
            StatusUpdate status = new StatusUpdate(text);
            ContentResolver resolver = getContentResolver();
            final StatusUpdate status2 = new StatusUpdate("画像投稿のテスト");
            status.media(new File(filePath));
            mTwitter.updateStatus(status);
		}
		}
		
