package com.ab.autobeauty;

import com.ab.autobeautyExit.ProjectExit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Process;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ViewSwitcher.ViewFactory;

public class ShowActivity extends Activity implements OnItemSelectedListener,
		ViewFactory {
	private ImageSwitcher is;
	private Gallery gallery;
	private String chose;
	private String chosesl;
	private ImageButton ibtn, imagebutton;
	
	
//	private SearchView searchView;

	// private Integer[] mThumbIds = { R.drawable.b, R.drawable.c, R.drawable.d
	// };

	private Integer[] mImageIds;

	private Integer[] mImageIds1 = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e };
	private Integer[] mImageIds2 = { R.drawable.b, R.drawable.c, R.drawable.d };
	private Integer[] mImageIds3 = { R.drawable.c, R.drawable.d, R.drawable.e };
	private Integer[] mImageIds4 = { R.drawable.a, R.drawable.d, R.drawable.c };
	private Integer[] mImageIds5 = { R.drawable.e, R.drawable.d, R.drawable.b };

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.showcar);
		
		ProjectExit.getInstance().addActivity(this);
		//搜索 按钮
//		searchView = (SearchView) findViewById(R.id.searchView1);
//		searchView.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent i = new Intent(ShowActivity.this, SampleSearchActivity.class);
//				startActivity(i);
//				ShowActivity.this.finish();
//			}
//		});
		
		imagebutton = (ImageButton) findViewById(R.id.imagebutton);
		imagebutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ShowActivity.this, SampleSearchActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		ibtn = (ImageButton) findViewById(R.id.top_goback_ibtn);
		ibtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ShowActivity.this, AutoBeautyActivity.class);
				startActivity(i);
				ShowActivity.this.finish();
			}
		});
		
		Intent intent = getIntent();
		chose = intent.getStringExtra("chose");
		chosesl = intent.getStringExtra("chosesl");

		if (chosesl != null) {
			switch (chosesl) {
			case "车之臣":
				mImageIds = mImageIds2;
				break;
			case "典雅黑":
				mImageIds = mImageIds3;
				break;
			case "运动款":
				mImageIds = mImageIds4;
				break;
			case "小清新":
				mImageIds = mImageIds3;
				break;
			case "皮质成品":
				mImageIds = mImageIds3;
				break;
			case "非皮质成品":
				mImageIds = mImageIds3;
				break;
			case "真皮手缝":
				mImageIds = mImageIds3;
				break;
			case "车尾图标":
				mImageIds = mImageIds3;
				break;
			case "标志图案":
				mImageIds = mImageIds3;
				break;
			case "车身LOGO":
				mImageIds = mImageIds3;
				break;
			case "车身拉花":
				mImageIds = mImageIds3;
				break;
			case "娃娃":
				mImageIds = mImageIds3;
				break;
			case "香水座":
				mImageIds = mImageIds3;
				break;
			case "鸿盛":
				mImageIds = mImageIds3;
				break;
			case "玉器":
				mImageIds = mImageIds3;
				break;
			case "其它精品":
				mImageIds = mImageIds3;
				break;

			default:
				break;
			}
		}

		if (chose != null) {
			switch (chose) {
			case "轮毂":
				mImageIds = mImageIds1;
				break;
			case "鲨鱼鳍":
				mImageIds = mImageIds5;
				break;
			case "创意装饰":
				mImageIds = mImageIds5;
				break;
			case "优惠活动":
				mImageIds = mImageIds5;
				break;
//			case "座垫":
//				mImageIds = mImageIds2;
//				break;
//			case "方向盘套":
//				mImageIds = mImageIds3;
//				break;
//			case "车贴拉花":
//				mImageIds = mImageIds4;
//				break;

//			case "内饰":
//				mImageIds = mImageIds5;
//				break;

			default:
				break;
			}
		}

		// Reference the Gallery view
		Gallery g = (Gallery) findViewById(R.id.gallery);
		// Set the adapter to our custom adapter (below)
		g.setAdapter(new ImageAdapter(this));

		// Set a item click listener, and just Toast the clicked position
		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				// Toast.makeText(ShowActivity.this, "" + position,
				// Toast.LENGTH_SHORT).show();
			}
		});

		// We also want to show context menu for longpressed items in the
		// gallery
		registerForContextMenu(g);
	}

	// @Override
	// public void onCreateContextMenu(ContextMenu menu, View v,
	// ContextMenuInfo menuInfo) {
	// menu.add(R.string.gallery_2_text);
	// }

	// @Override
	// public boolean onContextItemSelected(MenuItem item) {
	// AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
	// .getMenuInfo();
	// Toast.makeText(this, "Longpress: " + info.position, Toast.LENGTH_SHORT)
	// .show();
	// return true;
	// }

	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
			// See res/values/attrs.xml for the <declare-styleable> that defines
			// Gallery1.
			TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
			mGalleryItemBackground = a.getResourceId(
					R.styleable.Gallery1_android_galleryItemBackground, 0);
			a.recycle();
		}

		public int getCount() {
			return mImageIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView i = new ImageView(mContext);

			i.setImageResource(mImageIds[position]);
			i.setScaleType(ImageView.ScaleType.FIT_XY);
			i.setLayoutParams(new Gallery.LayoutParams(800, 600));

			// The preferred Gallery item background
			i.setBackgroundResource(mGalleryItemBackground);

			return i;
		}

	}

	@Override
	public View makeView() {
		return null;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.auto_beauty, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			AlertDialog.Builder build = new AlertDialog.Builder(this);
			build.setTitle("系统提示").setMessage("确定要退出吗？");
			build.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							ProjectExit.getInstance().exit();
						}
					});
			build.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					}).show();

		}
		return true;
	}
}
