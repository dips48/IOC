package com.dips.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dips.base.BeanAttr;
import com.dips.base.ConstructArgs;

public class XMLparser {
	public static void main(String args[]) throws Exception{
		getBean("bean.xml");
	}
	public static HashMap<String,BeanAttr> getBean(String path) throws Exception{
		HashMap<String,BeanAttr> baArray=new HashMap<String,BeanAttr> ();
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder db = dbf.newDocumentBuilder();  
	        Document document = db.parse(new File(path));  
	        NodeList list = document.getElementsByTagName("bean"); 
	        int size=list.getLength();
	        int tl=0;
	        int cs=0;
	        while(tl<size){
	        for(int i = 0; i < list.getLength(); i++)  
	        {  boolean flag=true;
	        	Element el=(Element)list.item(i);
	        	BeanAttr ba=new BeanAttr();
	        	ba.setBeanID(el.getAttribute("id"));
	        	if(el.getAttribute("id").equals("")){
	        		ba.setBeanID(el.getAttribute("class"));
	        		ba.setScopetype("prototype");
	        	}
	        	if(baArray.get(ba.getBeanID())!=null){
	        		flag=false;
	        	}
	        	ba.setClassPath(el.getAttribute("class"));
	        	ba.setInitmethod(el.getAttribute("init-method"));
	        	ba.setDestroymethod(el.getAttribute("destroy-method"));
	        	ba.setFactorymethod(el.getAttribute("factory-method"));
	        	ba.setScopetype(el.getAttribute("scope"));
	            NodeList cList =el.getElementsByTagName("constructor-arg");
	            if(cList.getLength()>0){
//	            	ConstructArgs ca=new ConstructArgs();
	            for(int j=0;j<cList.getLength();j++){
	            	Element cel=(Element)cList.item(j);
	            	if(cel.getAttribute("value").equals("")){
	            		if(baArray.get(cel.getAttribute("ref"))!=null){
	            			ba.getCa().getList().add(baArray.get(cel.getAttribute("ref")));
	            		}else{
	            			flag=false;
	            		}
	            	}else{
	            		ba.getCa().getList().add(cel.getAttribute("value"));
	            	}
	            }
	            }
	            NodeList pList=el.getElementsByTagName("property");
	            if(pList.getLength()>0){
	            	for(int k=0;k<pList.getLength();k++){
	            		
	            		
	            		
	            		Element pel=(Element)pList.item(k);
	            		
	            		
	            		
	            		
	            		if(pel.getAttribute("value").equals("")){
		            		if(!pel.getAttribute("ref").equals("")){
		            			if(baArray.get(pel.getAttribute("ref"))!=null){
		            			ba.getAttr().put(pel.getAttribute("name"),baArray.get(pel.getAttribute("ref")));
		            			}else{
		            				flag=false;;
		            			}
		            		}else if(pel.getElementsByTagName("bean")!=null){
		            			Element bel=(Element)pel.getElementsByTagName("bean").item(0);
		            			if(baArray.get(bel.getAttribute("class"))!=null){
			            			ba.getAttr().put(pel.getAttribute("name"),baArray.get(bel.getAttribute("class")));
			            		}else{
			            			flag=false;
			            		}
		            			
		            		}else{
		            			flag=false;
		            		}
		            	}else{
		            		ba.getAttr().put(pel.getAttribute("name"),(String)pel.getAttribute("value"));
		            	}
	            		 
	            		
	            	}
	            }
	            if(flag){
	            tl++;
	            baArray.put(ba.getBeanID(), ba);}
	        }
	        cs++;
	        if(cs>size){
	        	System.exit(-1);
	        }
	        }
//	        Iterator it=baArray.entrySet().iterator();
//	        int gs=0;
//			while(it.hasNext()){
//				gs++;
//				System.out.println("µÚ"+gs+"¸ö");
//				Map.Entry entry = (Map.Entry) it.next();
//				((BeanAttr)entry.getValue()).toString();
//			}   
	     
		return baArray;
	}
}
