package com.java.accessModifier;

public class accessModifier_Protected {
	/*Note:
	The protected access modifier is accessible within package and outside the package but through inheritance only.
	The protected access modifier can be applied on the data member, method and constructor. It can't be applied on the class.
	It provides more accessibility than the default modifer*/
	protected  void msg123() {
		System.out.println("Hello i am in protected");
	}
	
	protected int a=7;
	
	protected void accessModifier_Protected(){
		System.out.println("This is a constrcutor specifed with protected Access modifier");
	}
	
	protected void accessModifier_Protected(int a , int b){
		System.out.println("This is a argumented constrcutor specifed with protected Access modifier");
		System.out.println(a+b);
	}

}
