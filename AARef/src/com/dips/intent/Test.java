package com.dips.intent;

import com.dips.startup.MyContext;



public class Test {
	public static void main(String args[]) throws Exception{
		//ԭDemo������Spring�У���������Springʹ��������� ApplicationContext ctx=new FileSystemXmlApplicationContext("bean.xml");
//		ApplicationContext ctx=new FileSystemXmlApplicationContext("bean.xml");//Spring����
		MyContext ctx=new MyContext("bean.xml");
		//ͨ��beanID��ȡ����
		Juggler performer=(Juggler)ctx.getBean("duke");
		performer.perform();
		//�͵�һ��Ϊͬ����class����ͬ��ID����֤����ֻ�������beanID
		Juggler performer1=(Juggler)ctx.getBean("duk");
		performer1.perform();
		//�޸�xml��scope=��prototype��������ÿ�ζ����ù��췽��
		Juggler performer2=(Juggler)ctx.getBean("duke");
		performer2.perform();
		//ʹ���ض����췽����ȡʵ��
		Performer poeticDuke=(Performer)ctx.getBean("poeticDuke");
		poeticDuke.perform();
		//ʹ�ù���������ȡʵ��
		Stage stage=(Stage)ctx.getBean("theStage");
		Auditorium au=(Auditorium)ctx.getBean("auditorium");
		//ָ������ֵ
		Instrumentalist ist=(Instrumentalist)ctx.getBean("Instrumentalist");
		ist.perform();
		//��֤prototypeֻ�Ƕ��ڸ�bean�Ĺ��췽�����ԣ��౾��Ϊ�������ⲻ��ı�
		NotSingle ns=(NotSingle)ctx.getBean("single");
		
		NotSingle ns1=(NotSingle)ctx.getBean("single");	
		
		if(ns==ns1){System.out.println("��ͬ�Ķ���");}else{System.out.println("��ͬ�Ķ���");}
	}
}
