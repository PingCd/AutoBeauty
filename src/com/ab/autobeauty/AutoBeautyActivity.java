package com.ab.autobeauty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ab.autobeautyExit.ProjectExit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Process;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AutoBeautyActivity extends Activity {

	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合

	private String[] titles; // 图片标题
	private int[] imageResId; // 图片ID
	private List<View> dots; // 图片标题正文的那些点

	private TextView tv_title;
	private int currentItem = 0; // 当前图片的索引号

	private GridView gridview;

	private String texts[] = null;
	private int images[] = null;

	private ImageButton imagebutton;
	
	
	// private SearchView searchView;

	// private ListView ls_table; // 美容服务类别列表
	// private LayoutInflater inflater;

	// An ExecutorService that can schedule commands to run after a given delay,
	// or to execute periodically.
	private ScheduledExecutorService scheduledExecutorService;

	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auto_beauty);
		// // 如果当前的状态为 null
		// if (savedInstanceState == null) {
		// getFragmentManager().beginTransaction()
		// .add(R.id.container, new PlaceholderFragment()).commit();
	
		ProjectExit.getInstance().addActivity(this);
		
		imagebutton = (ImageButton) findViewById(R.id.imagebutton);
		imagebutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AutoBeautyActivity.this,
						SampleSearchActivity.class);
				startActivity(intent);

			}
		});
		// 搜索 按钮
		// searchView = (SearchView) findViewById(R.id.searchView1);
		// searchView.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent i = new Intent(AutoBeautyActivity.this,
		// SampleSearchActivity.class);
		// startActivity(i);
		//
		// }
		// });

		imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d, R.drawable.e };
		titles = new String[imageResId.length];
		titles[0] = "xx美容1";
		titles[1] = "xx美容2";
		titles[2] = "xx美容3";
		titles[3] = "xx美容4";
		titles[4] = "xx美容5";

		imageViews = new ArrayList<ImageView>();

		// 初始化图片资源
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}

		dots = new ArrayList<View>();
		dots.add(findViewById(R.id.v_dot0));
		dots.add(findViewById(R.id.v_dot1));
		dots.add(findViewById(R.id.v_dot2));
		dots.add(findViewById(R.id.v_dot3));
		dots.add(findViewById(R.id.v_dot4));

		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(titles[0]);

		viewPager = (ViewPager) findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());

		images = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
				R.drawable.q, R.drawable.w };
		texts = new String[] { "轮毂", "座垫", "方向盘套", "车贴拉花", "鲨鱼鳍", "内饰", "创意装饰",
				"店面介绍", "优惠活动" };

		gridview = (GridView) findViewById(R.id.gridview);
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 9; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			lstImageItem.add(map);
		}

		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,// 数据源
				R.layout.item,// 显示布局
				new String[] { "itemImage", "itemText" }, new int[] {
						R.id.iv_item, R.id.tv_name });
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());

		// }
	}

	class ItemClickListener implements OnItemClickListener {
		/**
		 * 点击项时触发事件
		 * 
		 * @param parent
		 *            发生点击动作的AdapterView
		 * @param view
		 *            在AdapterView中被点击的视图(它是由adapter提供的一个视图)。
		 * @param position
		 *            视图在adapter中的位置。
		 * @param rowid
		 *            被点击元素的行id。
		 */
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long rowid) {
			HashMap<String, Object> item = (HashMap<String, Object>) parent
					.getItemAtPosition(position);
			// 获取数据源的属性值
			String itemText = (String) item.get("itemText");
			Object object = item.get("itemImage");
			Toast.makeText(AutoBeautyActivity.this, itemText, Toast.LENGTH_LONG)
					.show();
			Intent intent = new Intent();
			intent.setClass(AutoBeautyActivity.this, ShowActivity.class);
			intent.putExtra("chose", texts[position]);
			

			Intent intent_ls = new Intent();
			intent_ls.setClass(AutoBeautyActivity.this, SelectActivity.class);
			intent_ls.putExtra("chose", texts[position]);
			

			Intent i = new Intent(AutoBeautyActivity.this, ThirdActivity.class);
			

			// 根据图片进行相应的跳转
			switch (images[position]) {
			case R.drawable.a:

				startActivity(intent);// 启动另一个Activity

				break;
			case R.drawable.b:
				startActivity(intent_ls);

				break;
			case R.drawable.c:

				startActivity(intent_ls);

				break;

			case R.drawable.d:

				startActivity(intent_ls);

				break;

			case R.drawable.e:

				startActivity(intent);

				break;
			case R.drawable.f:

				startActivity(intent_ls);

				break;
			case R.drawable.g:

				startActivity(intent);

				break;
			case R.drawable.q:

				startActivity(i);

				break;
			case R.drawable.w:

				startActivity(intent);

				break;

			}

		}
	}

	// private class LsAdapter extends BaseAdapter {
	//
	// public int getCount() {
	// // return 4;
	// return imageResId.length;
	// }
	//
	// public Object getItem(int position) {
	// return position;
	// }
	//
	// public long getItemId(int position) {
	// return position;
	// }
	//
	// public View getView(int position, View convertView, ViewGroup parent) {
	// View view = inflater.inflate(R.layout.item, null);
	//
	// ImageView iv_item = (ImageView) view.findViewById(R.id.iv_item);
	// TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
	//
	// TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
	// tv_name.setText(titles[position]);
	// TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
	// iv_item.setImageResource(imageResId[position]);
	//
	// return view;
	// }
	// }
	//

	@Override
	protected void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	protected void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				System.out.println("currentItem: " + currentItem);
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * 
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			tv_title.setText(titles[position]);
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * 
	 * 
	 */
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageResId.length;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {

		super.onConfigurationChanged(newConfig);

		Log.i("TAG", "I'm Android 4.0");

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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_auto_beauty,
					container, false);
			return rootView;
		}
	}

}
