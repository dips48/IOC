package com.dips.intent;

public class Stage {
	static int i=0;
	private Stage(){
		i++;
		System.out.println(i);
	}
	private static class StageSingletonHolder{
			static Stage instance=new Stage();
	}
	public static Stage getInstance(){
		System.out.println("初始化完成");
		return StageSingletonHolder.instance;
	}
}
