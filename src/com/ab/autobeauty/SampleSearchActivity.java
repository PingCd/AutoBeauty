package com.ab.autobeauty;

import com.ab.autobeauty.R;
import com.ab.autobeauty.R.layout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class SampleSearchActivity extends Activity {
	ActionBar aBar;
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.search);
		//标题作为返回主页按钮
		aBar = getActionBar();
		aBar.setDisplayShowHomeEnabled(true);
		aBar.setHomeButtonEnabled(true);
		aBar.setDisplayHomeAsUpEnabled(true);
		handleIntent(getIntent());  
	}  
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent i = new Intent(this, AutoBeautyActivity.class);
			startActivity(i);
			this.finish();
			break;
			
		case R.id.action_settings:
			AlertDialog.Builder build = new AlertDialog.Builder(this);
			build.setTitle("系统提示").setMessage("确定要退出吗？");
			build.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});
			build.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					}).show();

		default:
			break;
		}
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			AlertDialog.Builder build = new AlertDialog.Builder(this);
			build.setTitle("系统提示").setMessage("确定要退出吗？");
			build.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});
			build.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					}).show();

			return true;
		}
		return true;
	}
	
	public void onNewIntent(Intent intent) {  
		setIntent(intent);  
		handleIntent(intent);  
	}  
	public void onListItemClick(ListView l,  
		View v, int position, long id) {  
		// 点每个搜索结果时的处理代码  
	}  
	private void handleIntent(Intent intent) {  
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {  
		String query =  
		intent.getStringExtra(SearchManager.QUERY);  
		doSearch(query);  
		}  
	}  
	private void doSearch(String queryStr) {  
		//执行真正的查询结果处理  
		 
	}  
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.auto_beauty, menu);
		return true;
	}

	
}
