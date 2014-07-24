package com.dips.startup;

import java.util.HashMap;

import com.dips.base.BeanAttr;
import com.dips.ref.GetObject;
import com.dips.xml.XMLparser;

public class MyContext {
	HashMap<String,BeanAttr> objMap=new HashMap<String,BeanAttr> ();
	HashMap<String,Object> singleMap=new HashMap<String,Object> ();
	public Object getBean(String name) throws Exception{
		BeanAttr ba=objMap.get(name);
		if(ba.getScopetype().equals("")){
		if(singleMap.get(name)!=null){
			return singleMap.get(name);
		}else{
			Object obj=GetObject.getObject(ba);
			singleMap.put(name, obj);
			return obj;
		}}else if(ba.getScopetype().equals("prototype")){
			return GetObject.getObject(ba);
		}else{
			System.exit(-1);
			return null;
		}
	}
	public MyContext(String path) throws Exception{
		objMap=XMLparser.getBean(path);
	}
}
