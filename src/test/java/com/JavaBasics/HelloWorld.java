package com.JavaBasics;

public class HelloWorld {
	
	void add(int a, int b) {
		System.out.println(a+b);
	}
	
	int x=10;
	static int y=12;
	static void add(int a, int b,int c) {
		System.out.println(a+b+c);
	}

	public static void main(String[] args) {
		
		System.out.println("Welcome to Java-First Program");
		HelloWorld obj = new HelloWorld();
		obj.add(5, 8);
		add(3,6,8);
		System.out.println(obj.x);
		System.out.println(y);
	}

}
