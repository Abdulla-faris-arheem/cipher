package aca.main;

import aca.ciphers.*;
import aca.util.*;
import java.io.*;
import java.util.*;



public class encoder {
	
	/**
	 * Encoder default constructor
	 */
	public encoder()
	{
		this.english_dict=new ArrayList<String>();
		this.dict_size=0;
		
	}
	
	/**
	 * Encoder constructor
	 * 
	 * @param dict_file a dictionary file containing words in the dictionary, one per line
	 */
	public encoder(String dict_file)
	{
		english_dict=new ArrayList<String>();
		read_dict(dict_file);
	}
	
	/**
	 * Read a dictionary to memory. The result is the class member english_dict.
	 * @param dict_file a dictionary file containing words in the dictionary, one per line 
	 */
	public void read_dict(String dict_file)
	{
		try
		{
		   BufferedReader bf=new BufferedReader(new FileReader(dict_file));
		   String line;
		   while((line=bf.readLine())!=null)
		   {
			   english_dict.add(line.trim());
		   }
		   this.dict_size=english_dict.size();
		   bf.close();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		
	}
	
	/**
	 * Get a random word key from the dictionary with the length requirement
	 * 
	 * @param length the required length
	 * @return key generated 
	 */
	public String get_keyword_len(int length)
	{
		Random random_gen=new Random();
		Iterator<String> iter=english_dict.iterator();
		ArrayList<String> len_key=new ArrayList<String>();
		while(iter.hasNext())
		{
			String w=(String)iter.next();
			if(w.length()==length)
			{
				len_key.add(w);
			}
		}
		if(len_key.size()==0)
		{
			System.err.println("Cannot find key with length "+Integer.valueOf(length).toString()+" in the dictionary");
			return null;
		}
		else
		{
			 int index=random_gen.nextInt(len_key.size());
			 return len_key.get(index).toUpperCase();
		}
		//for(iterator<String> i=english_dict.)
	}
	
	/**
	 * Get random word key from the dictionary
	 * @return the key generated
	 */
	public String get_random_keyword()
	{
	    Random random_gen=new Random();
	    int index=random_gen.nextInt(dict_size);
	    return english_dict.get(index).toUpperCase();
	}
	
	private ArrayList<String> english_dict;
	private int dict_size=0;

	/**
	 * Encoder: main function, to encipher a file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	try
		{
		   Class c=Class.forName("aca.ciphers.BIFID");	  
		   Cipher cur_cipher=(Cipher) c.newInstance();
		 //  return cur_cipher.encode(plaintext);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}*/
	    if(args.length<3)
	    {
	    	System.err.println("Less than enough arguments");
	    	System.err.println("Arguments: input_data output_data dictionary");
	    }
	    File f=new File(args[2]);
	    if(!f.exists())
	    {
	    	System.err.println("No dictionary file found");
	    	System.exit(1);
	    }
	    encoder e = new encoder(args[2]);
	    e.get_encoded_data(args[0], args[1]);
	//    e.get_training_data(args[0],args[0]+".ciphered.train");
	//    e.get_test_data(args[0],args[0]+".ciphered.train.16class");
	 //   e.get_test_data(args[1], args[1]+".ciphered.test.16class");
	 //   System.out.println(args[0]);
	//    System.out.println(args[1]);
	}
	
	/**
	 * Get the cipher index
	 * @param c cipher name
	 * @return cipher id
	 */
	public static int get_encoder_index(String c)
	{
		return Generic_Func.search_str(encoder_list, c);
		/*if(Arrays.asList(encoder_list).contains(c))
		{
			return -1;
		}*/
		//else
		//{
		//	return Arrays.binarySearch(encoder_list, c);
		//}
		
	}
	
	public static HashMap<String,Integer> build_dict_map(String[] encoder_list)
	{
		HashMap<String,Integer> encoder_dict=new HashMap<String,Integer>();
		int index=0;
		for(String s:encoder_list)
		{
			encoder_dict.put(s, index);
			index++;
		}
		return encoder_dict;
	}
	
	
	
	public static int get_encoder_index(HashMap<String,Integer> dict,String str)
	{
		if(!dict.containsKey(str))
			return -1;
	  	return dict.get(str);
	}
	
//	public static HashMap<String,Integer> encoder_dict=new HashMap<String,Integer>();
//	public static String[] encoder_list={"Amsco","Autokey","Baconian","Bazeries","Beaufort","BIFID","Caesar",/*"Cadenus",*/"Checkerboard","CM_BIFID","Columnar","Foursquare","Tridigital","Trifid","Two_square","Variant","Vigenere"};
	//public static String[] encoder_list={"Amsco","Autokey","Baconian","Bazeries","Beaufort","BIFID","Caesar","Vigenere"};
	public static String[] encoder_list={"Amsco","Autokey","Baconian","Bazeries",
		"Beaufort","BIFID","Caesar","Cadenus","Checkerboard","CM_BIFID","Columnar",
		"Digrafid","Double_checkerboard","Foursquare","Frac_morse","Grandpre","Grille",
		"Gromark","Gronsfeld","Headlines","Homophonic","Incomp_column","Interrupted_key",
		"Key_phrase","Monome_dinome","Morbit","Myszkowski","Nicodemus","Nihilist_sub","Nihilist_trans",
		"Null","Period_Gromark","Phillips_C","Phillips_RC","Phillips","Playfair","Pollux",
		"Porta","Portax","Progressive_key","Quagmire_I","Quagmire_II","Quagmire_III","Quagmire_IV",
		"Rag_baby","Railfence","Redefence","Route_transposition","Running_key","Seriated_playfair",
		"Slidefair","Swagman","Tri_square",
		"Tridigital","Trifid","Two_square","Variant","Vigenere","Plaintext"};
	
	/**
	 * Check if the plain text is able to be enciphered using some specific cipher.
	 * @param plain plain text
	 * @param cipher_id cipher index
	 * @return True for being able to, False for not
	 */
	public boolean check_cipher(String plain, int cipher_id)
	{
		int plain_size=plain.length();
		//Cadenus
		if(cipher_id==7)
		{
			if(plain_size>150)
				return false;
		}
		//Gromark or Period Gromark
	/*	else if(cipher_id==17 || cipher_id==31)
		{
			if(plain_size<10)
				return false;
		}
	/*	else if(cipher_id==31)
		{
			if(plain_size>25)
				return false;
		}*/
		return true;
			
	}
	//public static String[] encoder_list={"Cadenus"};
	
/*	public void get_training_data(String infile,String outfile)
	{
		Preprocessor pre=new Preprocessor();
		try
		{
		   BufferedReader in = new BufferedReader(new FileReader(infile));
		   BufferedWriter out=new BufferedWriter(new FileWriter(outfile));
		   String line;
		  // int count=1;
			   while((line=in.readLine())!=null)
			   {
				  // System.out.println(line);
			/*	   if(line.startsWith("That's no problem,"))
				   {
					   System.out.println("test");
				   }*/
		/*		   String pre_line=pre.get_plain_no_blank(line);
				  //read plaintext and write encoded_text
				   for(int i=0;i<encoder_list.length;i++)
				   { 
					   String cipher_text=get_cipher_text(encoder_list[i],pre_line,pre);
					   String result=encoder_list[i]+"\t"+cipher_text+"\n";
					   out.write(result);
				   }
				   //System.out.println(count);
				  
				 //  count++;
			   }
			   in.close();
			   out.close();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			return;
		}
	
	}*/
	
	/**
	 * Get the cipher text from plain text.
	 * @param cipher_name cipher name
	 * @param plain plaintext
	 * @param pre preprocessor object to pre-process the plaintext
	 * @return cipher text
	 */
	public String get_cipher_text(String cipher_name,String plain,Preprocessor pre)
	{
        String plaintext;
        if(cipher_name=="Rag_baby")
        {
        	plaintext=pre.get_plain_with_blank(plain);
        }
        else
        {
		    plaintext=pre.get_plain_no_blank(plain);
        }
        if(plaintext.length()==0)
        {
        	System.err.println("Plain text does not any characters!");
        	System.err.println("Plain text:"+plain);
        	return null;
        }
		String cipher_pre="aca.ciphers.";
		try
		{
		   Class<?> c=Class.forName(cipher_pre+cipher_name);	  
		   Cipher cur_cipher=(Cipher) c.newInstance();
		   if(cur_cipher.key_need())
		   {
			   if(cipher_name=="Cadenus")
			   {
				   int key_len=plaintext.length()/25;
				   
				   if(plaintext.length()%25!=0)
				   {
					   key_len+=1;
				   }
				  // System.err.println(key_len);
				   String key=get_keyword_len(key_len);
				  // System.err.println(key);
				 /*  if(key_len==3)
				   {
					   key="AGA";
				   }*/
				 //  cur_cipher=new Cadenus(key);
				   
				   cur_cipher=(Cipher) c.getDeclaredConstructor(String.class).newInstance(key); 
				 //  ((Cadenus)cur_cipher).set_key_len(key_len);
			   }
			 /*  else if(cipher_name=="Checkboard")
			   {
				   String key1=get_keyword_len(5);
				   String key2=get_keyword_len(5); 
				   String keyword=get_random_keyword();
				   ArrayList<String> keys=new ArrayList<String>();
				   keys.add(key1);
				   keys.add(key2);
				   keys.add(keyword);
				   cur_cipher=(Cipher) c.getDeclaredConstructor(String.class).newInstance(keys);   
			   }*/
			   else if(cipher_name=="Null")
			   {
				   cur_cipher=(Cipher) c.getDeclaredConstructor(ArrayList.class).newInstance(this.english_dict);   
			//	   ((Null)cur_cipher).build_word_map(this.english_dict);
			   }
			   else if(cur_cipher.get_key_num()==1)
			   {
				   String new_key=get_random_keyword();
				   if(cur_cipher.get_key_len()!=null)
				   {
					   
					   new_key=get_keyword_len(cur_cipher.get_key_len().get(0));
				   }
				   //debug
				   System.err.println("Key");
				   System.err.println(new_key);
			     cur_cipher=(Cipher) c.getDeclaredConstructor(String.class).newInstance(new_key);
			   }
			   else
			   {
				   ArrayList<String> keys=new ArrayList<String>();
				   ArrayList<Integer> key_lens=cur_cipher.get_key_len();
				   if(key_lens!=null)
				   {
					   if(key_lens.size()<keys.size())
					   {
						   System.err.println("Not enough key length given");
						   return null;
					   }
				   }
				   
				   for(int i=0;i<cur_cipher.get_key_num();i++)
				   {
					   String new_key=get_random_keyword();
					   if(key_lens!=null && key_lens.get(i)!=-1)
					   {
						   new_key=get_keyword_len(key_lens.get(i));
					   }
					   keys.add(new_key);
				   }
				   //debug
				   System.err.println("Keys");
				   for(int i=0;i<keys.size();i++)
				   {
					   System.err.println(keys.get(i));
				   }
				  // Constructor
				   cur_cipher=(Cipher) c.getDeclaredConstructor(ArrayList.class).newInstance(keys);   
			   }
			//   cur_cipher=(Cipher)c.
		   }
		  /* if(plaintext=="itsatotaltotalmess")
		   {
			   System.err.println("test");
		   }*/
		   System.err.println(plaintext);
		   String cipher_text=cur_cipher.encode(plaintext);
		   System.err.println("Cipher text:"+cipher_text);
		   return cipher_text;
		   //return "";
		}
		catch(Exception e)
		{
			System.err.println(plaintext);
			//System.err.println("Cannot find the class");
			System.err.println(cipher_pre+cipher_name);
			//System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	

	
	/**
	 * Get the cipher text of a file and write it to another file
	 * @param infile input file
	 * @param outfile output file
	 */
	public void get_encoded_data(String infile,String outfile)
	{
		
		Preprocessor pre=new Preprocessor();
		Random random_gen=new Random();
		//int cipher_num=0;
		int cipher_num=encoder.encoder_list.length;
		try
		{
		   BufferedReader in = new BufferedReader(new FileReader(infile));
		   BufferedWriter out=new BufferedWriter(new FileWriter(outfile));
		   String line;
		   int count = 1;
			   while((line=in.readLine())!=null)
			   {
				 
				//   int cipher_id=0;
				   int cipher_id=random_gen.nextInt(cipher_num);//exclude plaintext
				   //use some random cipher_id to generate the test data
				  //read plaintext and write encoded_text
				  // for(int i=0;i<encoder_list.length;i++)
				  // {
				    if(cipher_id==cipher_num-1)
				    {
				    	//add plaintext				    	
				    	String result="Plaintext\t"+pre.get_plain_no_blank(line.trim()).toUpperCase()+"\n";
				    	out.write(result);
				    	System.err.println("Sent"+count+":Plain");
						System.err.println("Plain text:"+line.trim());
				    }
				    else
				    {
				      while(!check_cipher(line,cipher_id))
				      {
				    	cipher_id=random_gen.nextInt(cipher_num);
				      }		     
				      String cipher_name=encoder_list[cipher_id];
				  //    String cipher_name="Route_transposition";
				   //   System.err.println("Sent"+count+":"+cipher_name);
					//  System.err.println("Plain text:"+line.trim());
				   //  String cipher_name="Tri_square";
				    //  String cipher_name="Swagman";
					 
				      String cipher_text=get_cipher_text(cipher_name,line,pre);
					  String result=encoder_list[cipher_id]+"\t"+cipher_text+"\n";
					  if(result!=null)
					  {
					    out.write(result);
					  }
					
				  //  }
					
					count++;
				  }
			   }
			   in.close();
			   out.close();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			return;
		}
	}
	
	

}
