package com.dips.ref;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.Iterator;
import java.util.Map;

import com.dips.base.BeanAttr;
import com.dips.translate.Transform;

public class GetObject {
	public static String WEB_ROOT="C:\\Users\\xuyun.xy\\workspace\\AARef\\classes";
	public static Object getObject(BeanAttr ba) throws Exception{
		URL[] urls=new URL[1];
		URLStreamHandler streamHandler=null;
		File classPath=new File(WEB_ROOT);
		String repository =(new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString() ;
		URLClassLoader loader=new URLClassLoader(urls);
		Class myclass=loader.loadClass(ba.getClassPath());
		Object obj=null;
		if(!ba.getFactorymethod().equals("")){
			Method factory=myclass.getMethod(ba.getFactorymethod());
			 obj=factory.invoke(null);
		}else{
			//////////////构造方法调用
			if(ba.getCa().getList().size()==0){
				 obj=myclass.newInstance();
			}else{
				for(int i=0;i<ba.getCa().getList().size();i++){
					if(ba.getCa().getList().get(i) instanceof BeanAttr){
						ba.getCa().getList().set(i, getObject((BeanAttr)ba.getCa().getList().get(i)));
					}
				}
				 Constructor[] cons=myclass.getConstructors();
				 for(int i=0;i<cons.length;i++){
					 if(ba.getCa().ConvertFrom(cons[i])){
						 Object[] obArray=Transform.convert(ba.getCa(), cons[i].getParameterTypes());
						obj=cons[i].newInstance(obArray);
						 break;
					 }
				 }
				 //////
//				 for(int i=0;i<cons.length;i++){
//					 if(ba.getCa().SonOf(cons[i])){
//						 con=cons[i];
//					 }
//				 }
//				Object[] obArray=new Object[ba.getCa().getList().size()];
//				for(int i=0;i<ba.getCa().getList().size();i++){
//					obArray[i]=ba.getCa().getList().get(i);
//				}
//				 obj=con.newInstance(obArray);
			}
		}
		/////初始化参数
		Iterator ita=ba.getAttr().entrySet().iterator();
		while(ita.hasNext()){
			Map.Entry entrya = (Map.Entry) ita.next();
			String namea=(String)entrya.getKey();
			Object csa=entrya.getValue();
			if(csa instanceof BeanAttr){
				ba.getAttr().remove(namea);
				ba.getAttr().put(namea, getObject((BeanAttr)csa));
			}
		}
		Field[] fs = myclass.getDeclaredFields();
		if(ba.getAttr().size()>0){
			Iterator it=ba.getAttr().entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry) it.next();
				String name=(String)entry.getKey();
				Object cs=entry.getValue();
				Field f=null;
				for(Field fl:fs){
					if(fl.getName().equals(name)){
						f=fl;
						break;
					}
					
				}
				f.setAccessible(true);
			
				try{
				f.set(obj, cs);
				}catch(Exception e){
					while(true){
						int i=1;
						Object[] tlist=Transform.getObject((String)cs);
						try{
							f.set(obj, tlist[i]);
							break;
						}catch(Exception ex){
							i++;
						}
					
						}
				}
				}
		}
		
		//////运行初始化方法
		if(!ba.getInitmethod().equals("")){
			Method init=myclass.getMethod(ba.getInitmethod());
			init.invoke(obj);
		}
		/////////返回对象
		return obj;
		
	}
}
