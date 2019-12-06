package com.gjh.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.lang.model.element.Element;

public class AAAA {
	
	
	
	
	public static void uuid() {
        String  uuid = java.util.UUID.randomUUID().toString();
//        System.out.println(uuid);
        uuid = uuid.replaceAll("-", "").substring(0, 16);
        System.out.println(uuid);
        
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++) {
			uuid();
		}
	
	}
	

	
	
}