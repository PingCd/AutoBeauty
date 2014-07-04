package com.ab.autobeauty;

import com.ab.autobeauty.R;
import com.ab.autobeauty.R.layout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
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
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
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
}
