
package com.machao.paint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private WindowManager wm;
	
	private ImageView iv;
	private Button bt;
	private Bitmap baseBitmap;
	private Canvas canvas;
	private Paint paint;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		
		wm = getWindowManager();
		
		bt = (Button)findViewById(R.id.bt);
		iv = (ImageView)findViewById(R.id.iv);
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.GREEN);
		
//		baseBitmap = Bitmap.createBitmap(iv.getWidth(), iv.getHeight(), Bitmap.Config.ARGB_8888);
		baseBitmap = Bitmap.createBitmap(480, 631, Bitmap.Config.ARGB_8888);
//		System.out.println("iv.getWidth()="+iv.getWidth() + "  iv.getHeight()="+iv.getHeight());
		canvas = new Canvas(baseBitmap);
		canvas.drawColor(Color.WHITE);

 
		iv.setOnTouchListener(new View.OnTouchListener(){
			int startX, startY, newX, newY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
/*					baseBitmap = Bitmap.createBitmap(iv.getWidth(), iv.getHeight(), Bitmap.Config.ARGB_8888);
					System.out.println("iv.getWidth()="+iv.getWidth() + "  iv.getHeight()="+iv.getHeight());
					canvas = new Canvas(baseBitmap);
					canvas.drawColor(Color.WHITE);*/
					
					startX = (int)event.getX();
					startY = (int)event.getY();
										
					break;
				case MotionEvent.ACTION_MOVE:
					newX = (int)event.getX();
					newY = (int)event.getY();
					
					canvas.drawLine(startX, startY, newX, newY, paint);
					startX = (int)event.getX();
					startY = (int)event.getY();
					iv.setImageBitmap(baseBitmap);
					
					break;
				case MotionEvent.ACTION_UP:
					break;
				}
				
				return true;
			}
			
			
		});
		

	}

	
	public void save(View view){

		try {
			File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".jpg");
			FileOutputStream stream = new FileOutputStream(file);
			baseBitmap.compress(CompressFormat.JPEG, 100, stream);
			stream.close();
			Toast.makeText(this, "写文件成功", 1).show();
			
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "写文件失败", 1).show();
			e.printStackTrace();
		}
	}
	
	public void onClick(View view){
		Intent intent = new Intent();
		intent.setAction("android.intent.action.PICK");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setType("image/*");
		startActivityForResult(intent, 0);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
//		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			Uri uri = data.getData();
			System.out.println(uri.toString());
			
			try{
				InputStream is = getContentResolver().openInputStream(uri);
				int windowWidth = wm.getDefaultDisplay().getWidth();
				int windowHeight = wm.getDefaultDisplay().getHeight();
				BitmapFactory.Options opts = new Options();
				opts.inJustDecodeBounds = true;
				BitmapFactory.decodeStream(is, null, opts);
				int bitmapHeight = opts.outHeight;
				int bitmapWidth = opts.outWidth;
				if (bitmapHeight > windowHeight || bitmapWidth > windowWidth){
					int scaleX = bitmapWidth / windowWidth;
					int scaleY = bitmapHeight / windowHeight;
					
					opts.inSampleSize = scaleX > scaleY ? scaleX : scaleY;
				}
				else {
					opts.inSampleSize = 1;
				}
				opts.inJustDecodeBounds = false;
				
				is = getContentResolver().openInputStream(uri);
				Bitmap bitmap = BitmapFactory.decodeStream(is, null, opts);
				
//				baseBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
				canvas = new Canvas(baseBitmap);

				paint.setColor(Color.GREEN);
				canvas.drawBitmap(bitmap, new Matrix(), paint);
				canvas.drawText("我是拷贝的图片", 10, 10, paint);
				iv.setImageBitmap(baseBitmap);
				paint.setColor(Color.BLUE);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
}
