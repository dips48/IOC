package com.dips.intent;

public class NotSingle {
	private  static NotSingle instance;
	private NotSingle(){}
	public static NotSingle getInstance(){
		if(instance==null){
			instance=new NotSingle();
			System.out.println("开始初始化，构建第一个实例。。。");
		}
		return instance;
	}

}
