package com.ercin.example.memory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Repository;

import com.ercin.example.generated.OutputExampleList;



@Repository
public class ValueRepositoryImpl implements ValueRepository {
	
	
	private Map<String,String> valueMap=new HashMap<>();
	
	public  ValueRepositoryImpl() throws IOException {
		
		File file=new File("src/main/resources/settings.properties");
		BufferedReader reader=new BufferedReader(new FileReader(file));
        String satir = "";
        String aSatir[]=null;
		
            while (satir!=null) {
                satir = reader.readLine();
                if(satir!=null) {
                	aSatir=satir.split("=");
                    valueMap.put(aSatir[0], aSatir[1]);
                }
            }
            reader.close();
            
	}

	@Override
	public OutputExampleList getAll() {
		OutputExampleList outputExampleList=new OutputExampleList();
		
		Iterator iter = valueMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry mEntry = (Map.Entry) iter.next();
            outputExampleList.getKey().add(mEntry.getKey().toString());
			outputExampleList.getValue().add(mEntry.getValue().toString());
        }
		
		return outputExampleList;
			
	}
	
	@Override
	public String getValueByKey(String key) {
		String value="";
		for(int i=0; i<valueMap.size();i++) {
			value=valueMap.get(key);
		}
		if(value==null) {
			value="Girdiginiz keye ait value degeri bulunamadi.";
		}
		return value;
	}

}
