package com.ab.autobeautyExit;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class ProjectExit extends Application {
	// 添加一个集合用来装 启动的没个 Activity
	private List<Activity> activitylists = new LinkedList<Activity>();
	
	private static ProjectExit instance;
	
	private ProjectExit(){
		
	}
	//全局单例 唯一获取该 ProjectExit 的实例
	public static ProjectExit getInstance(){
		if (instance == null) {
			instance = new ProjectExit();
		}
		return instance;
	}
	// 在每个 Activity 中调用此方法 把自己添加到 集合中
	public void addActivity(Activity activity){
		activitylists.add(activity);
	}
	// 退出方法 循环遍历 集合里的每个 Activity 并关闭
	public void exit(){
		for (Activity activity : activitylists) {
			activity.finish();
		}
		System.exit(0);
	}
}
