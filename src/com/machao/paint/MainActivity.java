
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
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;


public class MainActivity extends Activity {
	private ImageView iv_1;
	private Canvas canvas;
	private Paint paint;
	private SeekBar sb1,sb2,sb3,sb4;
	private ColorMatrix cm;
	private Bitmap alterbitmap, bitmap1;
	private Matrix m;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		
		iv_1 = (ImageView)findViewById(R.id.iv_1);

		
		cm = new ColorMatrix();
		
		bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.face);
		alterbitmap = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), bitmap1.getConfig());
		
		FaceDetector detector = new FaceDetector(bitmap1.getWidth(), bitmap1.getHeight(), 6);
		Face mface[] = new Face[6];
		int mNumFaces = detector.findFaces(bitmap1, mface);
		
		if(mNumFaces > 0) {
			System.out.println("识别到了脸"+mNumFaces);
			for(int i = 0; i < mNumFaces; i++) {
				PointF point = new PointF();
				mface[i].getMidPoint(point);
				System.out.println("脸中心坐标" + point.x +":"+ point.y);
				
			}
		}
		else {
			System.out.println("没有识别到脸");
		}
		
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.GREEN);
//		paint.setColorFilter(new ColorMatrixColorFilter(cm));
		paint.setTextSize(40);
		paint.setTypeface(Typeface.DEFAULT);
		Path p = new Path();
		p.moveTo(20, 20);
		p.lineTo(100, 150);
		p.lineTo(200, 220);
		
		
		canvas = new Canvas(alterbitmap);
		m = new Matrix();
		canvas.drawBitmap(bitmap1, m, paint);
		canvas.drawTextOnPath("Hello this is text on a path", p, 0, 0, paint);
		
		iv_1.setImageBitmap(alterbitmap);
		


	}

	
	
	
	
}
