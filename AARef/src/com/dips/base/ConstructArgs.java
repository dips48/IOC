package com.dips.base;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ConstructArgs {
	int gs;
	List<Object> list=new ArrayList<Object> ();
	public int getGs() {
		return gs;
	}
	public void setGs(int gs) {
		this.gs = gs;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public boolean SonOf(Constructor ct){
		if(gs==0){
			return true;
		}
		Class[] cl=ct.getParameterTypes();
		for(int i=0;i<list.size();i++){
			//instanceof用法 存在问题，动态输入Class
			if(!list.get(i).getClass().isAssignableFrom(cl[i])){
				return false;
			}
		}
		return true;
	}
	
	public boolean ConvertFrom(Constructor ct){
	
		Class[] cl=ct.getParameterTypes();
		if(cl.length!=list.size()){
			return false;
		}
		System.out.println(cl.length);
		for(int i=0;i<list.size();i++){
			if(cl[i].toString().equals("int")){
				try{
					Integer.parseInt((String)list.get(i));
					continue;
				}catch(Exception e){
					return false;
				}
			}
			if(cl[i].toString().equals("double")){
				try{
					Double.parseDouble((String)list.get(i));
					continue;
				}catch(Exception e){
					return false;
				}
			}
			if(cl[i].toString().equals("float")){
				try{
					Float.parseFloat((String)list.get(i));
					continue;
				}catch(Exception e){
					return false;
				}
			}
			if(!cl[i].isAssignableFrom(list.get(i).getClass())){
				return false;
			}
		}
		return true;
	}
}
