package com.JavaBasics;

public class JavaOperators {

	int a =19,b=4;
	String str="java";
	char ch = 'a';
	boolean flag;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaOperators obj = new JavaOperators();
		int remainder = obj.a%obj.b;
		int quitoent = obj.a/obj.b;
		System.out.println("a%b: "+remainder);
		System.out.println("a/b: "+quitoent);
		
		int ternaryoperator = (obj.a<obj.b)?obj.a:obj.b; 
		System.out.println("ternaryoperator:"+ternaryoperator);
	}

}
