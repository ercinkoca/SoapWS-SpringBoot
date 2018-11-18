package com.ercin.example.memory;

import java.util.List;

import com.ercin.example.generated.OutputExampleList;



public interface ValueRepository {
	
	OutputExampleList getAll();
	String getValueByKey(String key);

}
