package com.java.accessModifier12;

import com.java.accessModifier.accessModifier_Protected;

//import com.java.accessModifier.accessModifierDefault;

class accessModifier_protected1 extends accessModifier_Protected {
	public static void main(String args[]) {
		accessModifier_protected1 obj = new accessModifier_protected1();
		obj.msg123();
		System.out.println(obj.a);
		obj.accessModifier_Protected();
		obj.accessModifier_Protected(4,5);
		//msg123();
	}
}