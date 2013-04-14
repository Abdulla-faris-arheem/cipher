package aca.ciphers;

import java.util.HashMap;

public class Morse_code {

	public Morse_code()
	{
		build_map();
	}
	
	public String get_morse_code(char c)
	{
		Character cur_c=Character.valueOf(c);
		if (morse_table.containsKey(cur_c))
		{
			return morse_table.get(cur_c);
		}
		else
		{
			return null;
		}
	}
	
	private void build_map()
	{
		morse_table.put('E',".");
		morse_table.put('T', "-");
		morse_table.put('I',"..");
		morse_table.put('A', ".-");
		morse_table.put('N',"-.");
		morse_table.put('M', "--");
		morse_table.put('S',"...");
		morse_table.put('U', "..-");
		morse_table.put('R',".-.");
		morse_table.put('W', ".--");
		morse_table.put('D',"-..");
		morse_table.put('K', "-.-");
		morse_table.put('G',"--.");
		morse_table.put('O', "---");
		morse_table.put('H',"....");
		morse_table.put('V', "...-");
		morse_table.put('F',"..-.");
		morse_table.put('L', ".-..");
		morse_table.put('P',".--.");
		morse_table.put('J', ".---");
		morse_table.put('B',"-...");
		morse_table.put('X', "-..-");
		morse_table.put('C',"-.-.");
		morse_table.put('Y', "-.--");
		morse_table.put('E',".");
		morse_table.put('T', "-");
		morse_table.put('E',".");
		morse_table.put('T', "-");
		morse_table.put('E',".");
		morse_table.put('T', "-");
		morse_table.put('E',".");
		morse_table.put('T', "-");
		morse_table.put('Z',"--..");
		morse_table.put('Q', "--.-");
		morse_table.put('1',".----");
		morse_table.put('2', "..---");
		morse_table.put('3',"...--");
		morse_table.put('4', "....-");
		morse_table.put('5',".....");
		morse_table.put('6', "-....");
		morse_table.put('7',"--...");
		morse_table.put('8', "---..");
		morse_table.put('9',"----.");
		morse_table.put('.', ".-.-.-");
		morse_table.put(',',"--..--");
		morse_table.put('?', "..--..");
		morse_table.put(':',"---...");
		morse_table.put(';', "-.-.-.");
		morse_table.put('-',"-....-");
		morse_table.put('/', "-..-.");
		morse_table.put('=',"-...-");
		morse_table.put('@', ".--.-.");
		morse_table.put('\"',".-..-.");
		morse_table.put('\'', ".----.");
	}
	
	public HashMap<Character,String> morse_table=new HashMap<Character,String>();
}
