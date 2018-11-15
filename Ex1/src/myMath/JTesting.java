package myMath;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class JTesting {
	Monom m1 = new Monom();
	Monom m2 = new Monom();
	Monom m3 = new Monom();
	Monom m4 = new Monom();
	Monom m5 = new Monom();
	Monom m6 = new Monom();
	Monom m7 = new Monom();

	Polynom p1 = new Polynom();
	Polynom p2 = new Polynom();
	Polynom p3 = new Polynom();
	Polynom p4 = new Polynom();
	Polynom p5 = new Polynom();
	Polynom p6 = new Polynom();
	Polynom p7 = new Polynom();
	@Before
	public void setUp() throws Exception
	{
		m1 = new Monom("7x");
		m2 = new Monom("14x");
		m2.add(m1);
		m3 = new Monom("3x");
		m4 = new Monom("3x");
		m4.multiply(m3);
		
		p1.add(m1);
		
		p2.add(m4);
		
		p3.add(p1);
		p3.add(p2);
		System.out.println(p3);
		
		p4.add(m1);
		p4.multiply(p2);
		p5 = (Polynom) p4.derivative();
	}
	
	@Test
	public void testString() {
		Assert.assertEquals("7x", m1.toString()); // test normal string
		Assert.assertEquals("21x", m2.toString()); // test add
		Assert.assertEquals("9x^2", m4.toString()); // test multiply
		
		Assert.assertEquals(m1.toString(), p1.toString()); // test Polynom add monom
		Assert.assertEquals("9x^2 + 7x", p3.toString()); // test Polynom add polynom
		Assert.assertEquals("63x^3", p4.toString()); // test multiply 
		Assert.assertEquals("189x^2", p5.toString()); // test derivative
		
		
		
	}
	
	
	
	
}
