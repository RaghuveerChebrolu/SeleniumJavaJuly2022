package com.OOPS.Basics;

class finalVariable {
	final int speedlimit = 90;// final variable

	void run() {
		//speedlimit = 400; compile time error
		System.out.println(speedlimit);
	}

	public static void main(String args[]) {
		finalVariable obj = new finalVariable();
		obj.run();
	}
}// end of class