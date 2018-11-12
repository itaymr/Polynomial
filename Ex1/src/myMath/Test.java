package myMath;

import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("MathTest");
		MathTest();
		System.out.println("StringTest");
		StringTest();
		

	}
	public static void StringTest()
	{
		
		Polynom p1 = new Polynom("3x");
		System.out.println(p1);
		
		Polynom p2 = new Polynom("3x + 5");
		System.out.println(p2);
		
		Polynom p3 = new Polynom("3x + 5x + 3");
		System.out.println(p3);
		p2.add(p3);
		System.out.println(p2);
	
	}
	
	public static void MathTest()
	{
		
		Polynom p1 = new Polynom();
		p1.add(new Monom(1, 3));
		p1.add(new Monom(2, 4));
		
		System.out.println("p1  = " + p1);
		
		Polynom p2 = new Polynom();
		p2.add(new Monom(1, 1));
		p2.add(new Monom(5, 5));
		
		System.out.println("p2 = " + p2);
		
		p1.multiply(p2);
		
		System.out.println("p1 * p2 = " + p1);
		
		System.out.println("p1 derivative =" + p1.derivative());
		
		Polynom p3 = new Polynom("3x^2 + 5 + 2x");
		
		System.out.println(p3);
		System.out.println("area " + p3.area(1, 3, 0.00001));
		
		
		
	}
	
}