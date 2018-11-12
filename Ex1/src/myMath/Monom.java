
package myMath;

import java.util.Set;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{


	/**
	 * 
	 * Constructor - sets the coefficient of the monom to DOUBLE a and INT b
	 * @param a - coefficient
	 * @param b - power
	 */

	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom()
	{
		this.set_coefficient(0);
		this.set_power(0);
	}

	public Monom(String m1)
	{
		m1.trim();
		m1 = m1.replace("\\+", "");
		String coeff = "";
		String pow = "";

		if(m1.contains("x"))
		{
			for(int i = 0; i <m1.length(); i++)
			{
				if(m1.charAt(i) == 'x')
				{
					coeff = m1.substring(0, i);
					if(coeff.equals("-"))
					{
						coeff = "-1";
					}
				}
			}
		}
		else 
		{
			this._coefficient = Double.parseDouble(m1);
			this._power = 0;
			return;
		}
		if(m1.contains("^"))
		{
			for(int i = 0; i <m1.length(); i++)
			{
				if(m1.charAt(i) == '^')
				{
					pow = m1.substring(i+1);
				}
			}
		}
		else
		{
			this._coefficient = Double.parseDouble(coeff);
			this._power = 1;
			return;
		}
		
		this._power = Integer.parseInt(pow);
		this._coefficient = Double.parseDouble(coeff);
		
		
		
		

	
	}

	/**
	 * Copy constructor - creates a new Monom with the coefficient and power of Monom ot
	 * @param ot - Monom to copy
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * Coefficient getter
	 * @return returns the coefficient of the monom
	 */
	public double get_coefficient()
	{
		return _coefficient;
	}
	/**
	 *  Power getter
	 * @return returns the power of the monom
	 */
	public int get_power()
	{
		return _power;
	}

	/**
	 * Multiplies the monom by -1, f(x) to -f(x)
	 */
	public void reverseMonom()
	{
		this._coefficient = -1 * _coefficient;


	}

	/**
	 * Checks if two monoms are equal
	 * @param m1 - Monom to compare
	 * @return returns TRUE if the Monoms have an equal coefficient AND power. Returns FALSE otherwise.
	 */
	public boolean equals(Monom m1)
	{
		if(m1.get_coefficient() == this._coefficient && m1.get_power() == this._power)
		{
			return true;
		}
		return false;

	}
	/**
	 * Add a monom - this function will only add a Polynom if the powers are equal
	 * @param m1 - Adds Monom m1 to the Monom this is called upon
	 */
	public void add(Monom m1)
	{
		if( m1.get_coefficient() == 0)
		{
			return;
		}

		if (m1.get_power() == this.get_power())
		{
			this._coefficient += m1.get_coefficient();
		}
		else
		{
			System.out.println("ERROR: powers not identical, cannot add these two monoms");
		}


	}

	/**
	 * Multiplies the monom with another monom
	 * @param m1 - Monom m1 to multiply
	 */
	public void multiply(Monom m1)
	{

		this._coefficient*=  m1.get_coefficient();
		this._power += m1.get_power();

	}

	/**
	 * ifMultiplied - a function to virtually multiply monoms - the monom itself will not change but will return
	 * the result of this multiplication as a new Monom
	 * @param m1 - the Monom to virtually multiply
	 * @return returns a new Monom, the result of the multiplication
	 */
	public Monom ifMultiplied(Monom m1)
	{
		return new Monom( m1.get_coefficient()*_coefficient, m1.get_power()+_power );
	}

	//****************** Private Methods and Data *****************

	/**
	 * Set the coefficient
	 * @param a - the new coefficient
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * Set the power 
	 * @param p - the new power
	 */
	private void set_power(int p) {
		this._power = p;
	}

	private double _coefficient; // 
	private int _power;

	/**
	 * Returns the value of f(x) when f(x) = ax^b
	 * @param x - the value to compute
	 * @return returns the value of f(x)
	 */
	public double f(double x) {

		return Math.pow(x, _power) * _coefficient;

	} 

	/**
	 * Convert the monom to a string
	 * @return Returns a stringed format of the Monom - ax^b
	 */

	public String toString()
	{

		String coeff = "" + this._coefficient;
		String pow = "" + this._power;
		
		if(_coefficient % 1 == 0)
		{
			coeff = "" + (int)this._coefficient;
		}
		
		if(coeff.equals("0") || coeff.equals("0.0"))
		{			
			return "0";
		}

		if(pow.equals("0"))
		{

			return coeff;

		}


		if(pow.equals("1")  || pow.equals("1.0"))
		{
			if(coeff.equals("1") || coeff.equals("1.0") || coeff.equals("-1") || coeff.equals("-1.0"))
			{
				if( coeff.equals("-1") || coeff.equals("-1.0"))
				{
					return "-x";
				}
				return "x";
			}
			return coeff + "x";
		}

		else
		{
			if(coeff.equals("1") || coeff.equals("1.0") || coeff.equals("-1") || coeff.equals("-1.0"))
			{
				if(coeff.equals("-1"))
				{
					return "-x^" + pow;
				}
				return "x^" + pow;
			}
			return coeff + "x^" + pow;
		}


	}


}
