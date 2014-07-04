package com.ab.autobeauty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class SelectActivity extends Activity {
	private ListView listSelect;
	private String chose;
	private String select[] = null;
	private LayoutInflater inflater;
	private Integer[] imageResId;
	private ImageButton ibtn, imagebutton;
	
//	private SearchView searchView;
	
	private Integer[] mImageIds1 = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_list);
		//搜索 按钮
//		searchView = (SearchView) findViewById(R.id.searchView1);
//		searchView.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent i = new Intent(SelectActivity.this, SampleSearchActivity.class);
//				startActivity(i);
//			}
//		});
		
		inflater = LayoutInflater.from(this);
		Intent intent = getIntent();
		chose = intent.getStringExtra("chose");
		
		imagebutton = (ImageButton) findViewById(R.id.imagebutton);
		imagebutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectActivity.this, SampleSearchActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		ibtn = (ImageButton) findViewById(R.id.top_goback_ibtn);
		ibtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(SelectActivity.this, AutoBeautyActivity.class);
				startActivity(intent1);
				SelectActivity.this.finish();
			}
		});
		
		switch (chose) {
		case "座垫":
			imageResId=mImageIds1;
			select = new String[] { "车之臣", "典雅黑", "运动款", "小清新" };
			break;

		case "方向盘套":
			imageResId = mImageIds1;
			select = new String[] { "皮质成品", "非皮质成品", "真皮手缝"};
			break;
			
		case "车贴拉花":
			imageResId = mImageIds1;
			select = new String[] { "车尾图标", "标志图案", "车身LOGO", "车身拉花"};
			break;
			
		case "鲨鱼鳍":
			imageResId = mImageIds1;
			select = new String[] { "", "", "", ""};
			break;
			
		case "内饰":
			imageResId = mImageIds1;
			select = new String[] { "娃娃", "香水座", "倒车镜吊坠"};
			
			break;
		
		case "创意装饰":
			imageResId = mImageIds1;
			select = new String[] { "", "", ""};
			break;
			
		case "倒车镜吊坠":
			imageResId = mImageIds1;
			select = new String[] { "鸿盛", "玉器", "其它精品"};
			break;
			
		default:
			break;
		}
	
	
		listSelect = (ListView) findViewById(R.id.listView);
		listSelect.setAdapter(new selectAdapter());
		listSelect.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				
				if (select[position].equals("倒车镜吊坠")) {
					Intent i = new Intent();
					i.setClass(SelectActivity.this, SelectActivity.class);
					i.putExtra("chose", select[position]);
					startActivity(i);
					
				}else{
					Intent intent = new Intent();
					intent.setClass(SelectActivity.this, ShowActivity.class);
					intent.putExtra("chosesl", select[position]);
					startActivity(intent);
				}
			}
		});
		

	}

	private class selectAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return select.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = inflater.inflate(R.layout.list_item, null);
			TextView sl_textview = (TextView) view.findViewById(R.id.textView);
			sl_textview.setText(select[position]);
			ImageView sl_imageview = (ImageView) view
					.findViewById(R.id.imageView);
			sl_imageview.setImageResource(imageResId[position]);
			return view;
		}
	}

}
