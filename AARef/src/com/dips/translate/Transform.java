package com.dips.translate;

import com.dips.base.ConstructArgs;

public class Transform {
	public static Class[] getClass(String str){
		int js=1;
		try{
			Integer.parseInt(str);
			js++;
			Class[] cl=new Class[2];
			cl[0]=str.getClass();
			cl[1]=Integer.class;
			return cl;
		}catch(Exception e){
			e.printStackTrace();
			Class[] cl=new Class[1];
			cl[0]=str.getClass();
			return cl;
		}
	}

	public static Object[] getObject(String cs) {
		Object[] cl=new Object[10];
		cl[0]=cs;
		int i=0;
		try{
			Integer.parseInt(cs);
			i++;
			cl[i]=new Integer(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			Float.parseFloat(cs);
			i++;
			cl[i]=new Float(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			Double.parseDouble(cs);
			i++;
			cl[i]=new Double(cs);
		}catch(Exception e){
			e.printStackTrace();
		}
		Object[] res=new Object[i+1];
		for(int j=0;j<=i;j++){
			res[j]=cl[j];
		}
		return  res;
	}
	
	public static Object[] convert(ConstructArgs ca,Class[] cl){
		Object[] objArray=new Object[cl.length];
		for(int i=0;i<cl.length;i++){
			if(cl[i].toString().equals("int")){
				objArray[i]=Integer.parseInt((String)ca.getList().get(i));
				continue;
			}
			if(cl[i].toString().equals("double")){
				objArray[i]=Double.parseDouble((String)ca.getList().get(i));
				continue;
			}
			if(cl[i].toString().equals("float")){
				objArray[i]=Float.parseFloat((String)ca.getList().get(i));
				continue;
			}
			objArray[i]=ca.getList().get(i);
		}
		return objArray;
	}
}
