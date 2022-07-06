package com.JavaBasics;

public class JavaVaraiables {

	int z;// instance variable of type integer declared outside a method and inside class
	static String str = "learningJava";// static variable of type String
	static char ch = 'h';// static variable of type character
	static int j = 7;// static variable of type integer
	boolean flag;
	
	//for single line comments
	
	/*
	 * i am about 
	 * commit the
	 * multiple line
	 */

	public static void printStatic() {
		System.out.println("Value of String valirable declared as static:" + str);
		System.out.println("Value of char valirable declared as static:" + ch);
	}

	public void sum(int a, int b, int c) {
		int Result = a + b + c;//local varaible of type Integer
		System.out.println("Result:" + Result);
	}

	public void sub(int a, int b) {
		int ResultOfSub = a - b;//local varaible of type Integer
		System.out.println("ResultOfSub:" + ResultOfSub);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaVaraiables obj = new JavaVaraiables();
		obj.sum(3, 4, 5);
		obj.sub(5, 1);
		System.out.println("z before initalizing:" + obj.z);
		obj.z = 9;
		System.out.println("z after initalizing:" + obj.z);
		printStatic();
		j++;
		System.out.println(j);
		System.out.println("boolean flag before initalizing:" + obj.flag);
		obj.flag=true;
		System.out.println("boolean flag after initalizing:" + obj.flag);
	}

}
