package aca.util;

public class Pair<T> {
	
	public Pair()
	{
		data1=null;
		data2=null;
	}
	
	public Pair (T d1,T d2)
	{
		data1=d1;
		data2=d2;
	}

	public T get_first()
	{
		return data1;
	}
	
	public T get_second()
	{
		return data2;
	}
	
	public void set_first(T d1)
	{
		data1=d1;
	}
	
	public void set_second(T d2)
	{
		data2=d2;
	}
	
	private T data1;
	private T data2;
}
