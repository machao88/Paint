
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
	
	private ImageView iv_after, iv_pre;
	private Bitmap afterbitmap;
	private Canvas canvas;
	private Paint paint;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		
		iv_after = (ImageView)findViewById(R.id.iv_after);
		iv_pre = (ImageView)findViewById(R.id.iv_pre);
		
		BitmapFactory.Options opts = new Options();
		opts.inSampleSize = 5;
		
		Bitmap after = BitmapFactory.decodeResource(getResources(), R.drawable.after, opts);
		Bitmap pre = BitmapFactory.decodeResource(getResources(), R.drawable.pre, opts);
		
		afterbitmap = Bitmap.createBitmap(pre.getWidth(), pre.getHeight(), pre.getConfig());
		
		
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.BLACK);
		
		canvas = new Canvas(afterbitmap);
//		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(pre, new Matrix(), paint);
		
		iv_after.setImageBitmap(after);
		iv_pre.setImageBitmap(afterbitmap);

 
		iv_pre.setOnTouchListener(new View.OnTouchListener(){
			int startX, startY, newX, newY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:		
					break;
				case MotionEvent.ACTION_MOVE:
					newX = (int)event.getX();
					newY = (int)event.getY();
					for(int i = -8; i < 8; i++)
						for(int j = -8; j < 8; j++)
							if((newX + i > 0 && newX + i < afterbitmap.getWidth()) 
									&& (newY + j > 0 && newY + j < afterbitmap.getHeight()))
								afterbitmap.setPixel(newX + i, newY + j, Color.TRANSPARENT);
					iv_pre.setImageBitmap(afterbitmap);
					break;
				case MotionEvent.ACTION_UP:
					break;
				}
				
				return true;
			}
			
			
		});
		

	}

	
	
	
	
}
