package com.dips.intent;

import com.dips.startup.MyContext;



public class Test {
	public static void main(String args[]) throws Exception{
		//原Demo运行在Spring中，区别在于Spring使用这个容器 ApplicationContext ctx=new FileSystemXmlApplicationContext("bean.xml");
//		ApplicationContext ctx=new FileSystemXmlApplicationContext("bean.xml");//Spring方法
		MyContext ctx=new MyContext("bean.xml");
		//通过beanID获取对象
		Juggler performer=(Juggler)ctx.getBean("duke");
		performer.perform();
		//和第一次为同样的class，不同的ID，验证单例只是针对于beanID
		Juggler performer1=(Juggler)ctx.getBean("duk");
		performer1.perform();
		//修改xml，scope=”prototype“，可以每次都调用构造方法
		Juggler performer2=(Juggler)ctx.getBean("duke");
		performer2.perform();
		//使用特定构造方法获取实例
		Performer poeticDuke=(Performer)ctx.getBean("poeticDuke");
		poeticDuke.perform();
		//使用工厂方法获取实例
		Stage stage=(Stage)ctx.getBean("theStage");
		Auditorium au=(Auditorium)ctx.getBean("auditorium");
		//指定属性值
		Instrumentalist ist=(Instrumentalist)ctx.getBean("Instrumentalist");
		ist.perform();
		//验证prototype只是对于该bean的构造方法而言，类本身为单例，这不会改变
		NotSingle ns=(NotSingle)ctx.getBean("single");
		
		NotSingle ns1=(NotSingle)ctx.getBean("single");	
		
		if(ns==ns1){System.out.println("相同的对象");}else{System.out.println("不同的对象");}
	}
}
