package com.zerv.unitest;

import java.util.*;
import java.util.Map;
import java.util.stream.Stream;

import com.zerv.excel.utils.SpreadSheetBaseFunction;

public class MapTest {

	public static void main(String[] args) {
		
		String str="Article_1";
		
		String str1=str.substring(str.indexOf('_')+1);
		//System.out.println(str1);
		
		
		SpreadSheetBaseFunction sbf = new SpreadSheetBaseFunction();
		Map<String, String> bibilioData;
		List<String> li=new ArrayList<String>(); 
		String data="data1";
		bibilioData = sbf.getTableValues("ScholarlyArtifact", "ScholarlyArtifact", Integer.parseInt(data.substring(4)));
		for (String key : bibilioData.keySet()) {			
			li.add(key);
		}
			for (int i = 0; i < li.size(); i++) {
				System.out.println(li.get(i));
			}
			
		}
		
/*		
Object[] array = bibilioData.keySet().toArray();
System.out.println(array.toString());
	
	
		Stream.of(bibilioData.keySet().toArray()).forEach(x -> x.toString().substring(x.toString().indexOf('_')+1));
	*/
	}


