public class MyException extends Exception 
{
	public char c;
	
	MyException(char var)
	{
		c=var;
	}
	
	public String toString()
	{
		return "Error in Input: Entered "+c;
	}

}
