package com.dips.intent;

public class NotSingle {
	private  static NotSingle instance;
	private NotSingle(){}
	public static NotSingle getInstance(){
		if(instance==null){
			instance=new NotSingle();
			System.out.println("��ʼ��ʼ����������һ��ʵ��������");
		}
		return instance;
	}

}
