package myMath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{



	private int size = 0;
	private ArrayList<Monom> _monoms = new ArrayList<Monom>();


	/**
	 * Constructor - constructs string pol into a polynom
	 * @param pol - the string to convert into a polynom
	 */
	public Polynom(String pol)
	{
		pol = pol.replaceAll("\\s+","");
		pol = pol.replace("+-", "-");

		int j = 0;

		if(pol.equals("0") || pol.equals("0.0"))
		{
			this._monoms.add(new Monom(0,0));
			return;
		}

		boolean flag = false;
		for(int i = 0; i < pol.length(); i++)
		{
			if(pol.charAt(i) == '-' || pol.charAt(i) == '+')
			{
				flag = true;

				if(i == 0)
				{
					continue;	
				}

				this._monoms.add(new Monom(pol.substring(j, i)));
				j=i;
			}
		}
		if(flag)
		{
			_monoms.add(new Monom(pol.substring(j)));
		}
		else
		{
			_monoms.add(new Monom(pol));
		}
		
		this.fix();
	}
	/**
	 * Constructor - constructs a list with a list of polynoms, this allows you to create an array list of monoms, and add them all together
	 *
	 * @param monomList
	 */
	public Polynom(ArrayList<Monom> monomList) { 

		this._monoms.addAll(monomList);
		this.fix();
	}

	/**
	 * Constructor - creates the 0 polynom. Adds a monom(0,0) to this Polynom. It will be deleted when functions are used upon it
	 */
	public Polynom()
	{
		_monoms.add(new Monom(0,0));
	}

	/**
	 * Copy constructor - copies another polynom
	 * @param polynom - the polynom to copy
	 */
	public Polynom(Polynom polynom) { // copy constructor


		for(Monom m : polynom._monoms)
		{
			this._monoms.add(m);

		}
		this.fix(); 

	}
	/**
	 * Constructs a copy polynom using another polynom_able variable
	 * @param p1 - the Polynom_able to copy
	 */

	public Polynom(Polynom_able p1) {

		Iterator<Monom> it = p1.iteretor();


		while(it.hasNext())
		{
			_monoms.add(it.next());
		}
		this.fix();

	}

	/**
	 * 
	 * Constructor - constructs a polynom with an iterator
	 * @param iteretor - the iterator to construct the polynom with
	 */
	public Polynom(Iterator<Monom> iteretor) {

		Monom element;
		while(iteretor.hasNext())
		{
			element = new Monom(iteretor.next());
			this._monoms.add(element);

		}
		this.fix();
	}

	/**
	 * Computes the value of f(x)
	 * @param x - the x to compute
	 * @return f - returns the value of f(x)
	 */
	public double f(double x) {

		double sum = 0;
		for(Monom m1 : _monoms)
		{
			sum += m1.f(x); // Calculates the value of each m1, then adds it to the sum

		}


		return sum;
	}

	/**Adds a polynom to another polynom
	 *@param p1 - the Polynom_able to add
	 *
	 */
	public void add(Polynom_able p1) {

		if(p1.isZero())
		{
			return;
		}
		Iterator<Monom> it = p1.iteretor();

		while(it.hasNext())
		{
			this.add(it.next());
		}


	}


	/**
	 * Adds a monom to the polynom
	 */
	public void add(Monom m1) {

		if(m1.get_coefficient() == 0) 
		{ 
			return; 
		}

		Iterator<Monom> it = iteretor();
		while(it.hasNext())
		{

			Monom element = it.next();
			if(element.get_power() == m1.get_power())
			{
				element.add(m1);
				if(element.get_coefficient() == 0)
				{
					_monoms.remove(element);
				}

				return;
			}

		}
		_monoms.add(m1);

	}

	/**
	 * Substracts a polynom
	 * @param p1 - the Polynom to substract
	 */
	public void substract(Polynom_able p1) {

		Polynom temp = new Polynom(p1.iteretor());
		temp.reversePolynom();

		this.add(temp);


	}
	/**
	 * Reverses this polynom. This will do -1 * f(x)
	 */
	public void reversePolynom()
	{
		for(Monom m1: _monoms)
		{
			m1.reverseMonom();
		}
		this.sort();


	}


	/**
	 * Multiplies a polynom with another polynom
	 * @param p1 - the polynom to multiply
	 */
	public void multiply(Polynom_able p1) {

		// This will iterate over this polynom, it will add each multiplication of the two polynoms into a new one
		// and then copy it to this one
		Polynom newPol = new Polynom(); 
		Iterator<Monom> it = p1.iteretor();
		Monom element;
		while(it.hasNext())
		{
			element = it.next();
			for(Monom m1: _monoms)
			{
				newPol.add(m1.ifMultiplied(element));

			}

		}

		_monoms = newPol._monoms;
		this.sort();

	}

	/**Compares polynoms, whether they are equa or not
	 * @param p1 - the Polynom to compare
	 * @return TRUE if they're equal FALSE otherwise
	 *
	 */
	public boolean equals(Polynom_able p1) {

		Polynom pol = new Polynom(p1);
		pol.sort();
		this.sort();

		if (pol._monoms.size() != _monoms.size())
		{
			return false;
		}

		for(int i = 0; i < _monoms.size();i++)
		{
			if (!(_monoms.get(i).equals(pol._monoms.get(i))))
			{
				return false;
			}

		}



		return true;
	}

	@Override
	public boolean isZero() {
		return this._monoms.isEmpty();


	}



	/**@param x0 - first number
	 * @param x1
	 * @param eps
	 * Calculates the root(approximately)
	 * @return the root
	 */
	@Override
	public double root(double x0, double x1, double eps) {

		double c= (x0 + x1 )/ 2;
		if(f(c) == 0 || (x1-x0 / 2 ) < eps )
		{
			return c;
		}
		else if ( ( f(c)<0 && f(x0)<0 ) || ( f(c)>0 && f(x0)>0 ) )
		{
			return root(c, x1, eps);
		}
		else
		{
			return root(x0, c, eps);
		}
		
		
	}

	@Override
	public Polynom_able copy() {

		return new Polynom(this);
	}

	@Override
	public Polynom_able derivative() {

		ArrayList<Monom> newList= new ArrayList<Monom>();


		for(Monom m1 : _monoms)
		{
			newList.add(new Monom(m1.get_coefficient()*m1.get_power(), m1.get_power() - 1));
		}

		return new Polynom(newList);
	}

	@Override
	public double area(double x0, double x1, double eps) {


		double n = (x1-x0) * (( f(x1) - f(x0) )/ eps); // Num of squares
		double dx = (x1-x0)/n;
		if(dx<0)
		{
			dx*=-1;
		}

		double sum = 0;

		double currX = x0;
		for(int i = 0; i < n; i++)
		{
			sum += f(currX) * dx; // calculate sum
			currX += dx; // increment x' + dx
		}
		return sum; 


	}

	@Override
	public Iterator<Monom> iteretor() {
		return _monoms.iterator();
	}


	private class MyIterator implements Iterator<Monom> {

		private int itCount = 0;

		public MyIterator()
		{
			itCount = 0;
		}

		@Override
		public boolean hasNext()
		{
			return itCount < size;
		}

		@Override
		public Monom next()

		{
			return  _monoms.get(itCount++);
		}



	}
	/**
	 * Fixes the function - removes all 0's
	 */

	public void fix()
	{
		if(_monoms.isEmpty())
		{
			return;
		}
		ArrayList<Monom> toRemove = new ArrayList<Monom>();
		
		Iterator it = iteretor();
		

		for(Monom m: _monoms)
		{
			if(m.get_coefficient() == 0)
			{
				toRemove.add(m);
			}
		}
		_monoms.removeAll(toRemove);

		this.sort();

	}


	/**
	 * Turns the polynom into a string
	 */
	public String toString()
	{
		String str = "";

		this.fix();

		boolean first = true;
		if(_monoms.size() == 1)
		{
			return _monoms.get(0).toString();
		}
		if(_monoms.size() == 0)
		{
			return "0";
		}
		for(Monom m: _monoms)
		{
			if(m.get_coefficient() > 0 )
			{
				if(first)
				{
					str+= m;
					first = false;
				}
				else
				{
					str+= " + " + m;
				}

			}
			else
			{
				Monom reverse = new Monom(m);
				reverse.reverseMonom();
				if(first)
				{
					str+= "-" + reverse;
					first = false;
				}
				else
				{
					str+= " - " + reverse;

				}
			}
		}


		return str;



	}

	/**
	 * Sorts the polynom by power value
	 */
	public void sort()
	{
		_monoms.sort(Comparator.comparingInt(Monom::get_power).reversed());

	}





}
