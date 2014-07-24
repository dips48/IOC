package com.dips.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BeanAttr {
	String beanID;
	String classPath="";
	String initmethod="";
	String destroymethod="";
	String factorymethod="";
	String scopetype="single";
	HashMap<String,Object> attr=new HashMap<String,Object> ();
	ConstructArgs ca=new ConstructArgs();
	public String toString(){
		System.out.println("beanIDΪ"+beanID);
		System.out.println("��·��Ϊ"+classPath);
		System.out.println("��ʼ������Ϊ:"+initmethod);
		System.out.println("���ٷ���Ϊ��"+destroymethod);
		System.out.println("��������Ϊ��"+factorymethod);
		System.out.println("����ʵ������Ϊ:"+scopetype);
		System.out.println("�����У�"+attr.size());
		System.out.println("���췽���У�"+ca.list.size());
	return "";
	}
	public Set<String> getDepand(){
		Set<String> res=new HashSet<String> ();
		Iterator it=attr.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry) it.next();
			if(entry.getValue() instanceof BeanAttr){
				res.add(((BeanAttr)entry.getValue()).beanID);
			}
		}
		if(ca!=null){
			for(Object obj:ca.list){
				if(obj instanceof BeanAttr){
					res.add(((BeanAttr)obj).beanID);
				}
			}
		}
		return res;
	}
	public String getInitmethod() {
		return initmethod;
	}
	public void setInitmethod(String initmethod) {
		this.initmethod = initmethod;
	}
	public String getDestroymethod() {
		return destroymethod;
	}
	public void setDestroymethod(String destroymethod) {
		this.destroymethod = destroymethod;
	}
	public String getFactorymethod() {
		return factorymethod;
	}
	public void setFactorymethod(String factorymethod) {
		this.factorymethod = factorymethod;
	}
	public String getScopetype() {
		return scopetype;
	}
	public void setScopetype(String scopetype) {
		this.scopetype = scopetype;
	}
	public String getBeanID() {
		return beanID;
	}
	public void setBeanID(String beanID) {
		this.beanID = beanID;
	}
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public HashMap<String, Object> getAttr() {
		return attr;
	}
	public void setAttr(HashMap<String, Object> hm) {
		this.attr = hm;
	}
	public ConstructArgs getCa() {
		return ca;
	}
	public void setCa(ConstructArgs ca) {
		this.ca = ca;
	}
}
