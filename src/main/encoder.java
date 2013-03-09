package main;

import aca.ciphers.*;
import aca.util.*;
import java.io.*;
import java.util.*;



public class encoder {
	
	public encoder()
	{
		
	}
	
	public encoder(String dict_file)
	{
		english_dict=new ArrayList<String>();
		read_dict(dict_file);
	}
	
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
	
	public String get_random_keyword()
	{
	    Random random_gen=new Random();
	    int index=random_gen.nextInt(dict_size);
	    return english_dict.get(index).toUpperCase();
	}
	
	private ArrayList<String> english_dict;
	private int dict_size=0;

	/**
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
	    	System.err.println("Less than 3 arguments");
	    }
	    File f=new File(args[2]);
	    if(!f.exists())
	    {
	    	System.err.println("No dictionary file found");
	    	System.exit(1);
	    }
	    encoder e = new encoder(args[2]);
	//    e.get_training_data(args[0],args[0]+".ciphered.train");
	    e.get_test_data(args[0],args[0]+".ciphered.train.16class");
	    e.get_test_data(args[1], args[1]+".ciphered.test.16class");
	 //   System.out.println(args[0]);
	//    System.out.println(args[1]);
	}
	
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
	
	//public static String[] encoder_list={"Amsco","Autokey","Baconian","Bazeries","Beaufort","BIFID","Caesar","Vigenere"};
	public static String[] encoder_list={"Amsco","Autokey","Baconian","Bazeries","Beaufort","BIFID","Caesar",/*"Cadenus",*/"Checkerboard","CM_BIFID","Columnar","Foursquare","Tridigital","Trifid","Two_square","Variant","Vigenere"};
	//public static String[] encoder_list={"Cadenus"};
	
	public void get_training_data(String infile,String outfile)
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
				   String pre_line=pre.get_plain_no_blank(line);
				  //read plaintext and write encoded_text
				   for(int i=0;i<encoder_list.length;i++)
				   { 
					   String cipher_text=get_cipher_text(encoder_list[i],pre_line);
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
	
	}
	
	public String get_cipher_text(String cipher_name,String plaintext)
	{
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
				   cur_cipher=(Cipher) c.getDeclaredConstructor(String.class).newInstance(key);   
			   }
			   else if(cipher_name=="Checkboard")
			   {
				   String key1=get_keyword_len(5);
				   String key2=get_keyword_len(5); 
				   String keyword=get_random_keyword();
				   ArrayList<String> keys=new ArrayList<String>();
				   keys.add(key1);
				   keys.add(key2);
				   keys.add(keyword);
				   cur_cipher=(Cipher) c.getDeclaredConstructor(String.class).newInstance(keys);   
			   }
			   else if(cur_cipher.get_key_num()==1)
			   {
				   String new_key=get_random_keyword();
				   if(cur_cipher.get_key_len()!=null)
				   {
					   new_key=get_keyword_len(cur_cipher.get_key_len().get(0));
				   }
			     
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
				  // Constructor
				   cur_cipher=(Cipher) c.getDeclaredConstructor(ArrayList.class).newInstance(keys);   
			   }
			//   cur_cipher=(Cipher)c.
		   }
		  /* if(plaintext=="itsatotaltotalmess")
		   {
			   System.err.println("test");
		   }*/
		   return cur_cipher.encode(plaintext);
		   //return "";
		}
		catch(Exception e)
		{
			System.err.println(plaintext);
			//System.err.println("Cannot find the class");
			System.err.println(cipher_pre+cipher_name);
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	
	
	public void get_test_data(String infile,String outfile)
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
		  // int count = 1;
			   while((line=in.readLine())!=null)
			   {
				   String pre_line=pre.get_plain_no_blank(line);
				//   int cipher_id=0;
				   int cipher_id=random_gen.nextInt(cipher_num);
				   //use some random cipher_id to generate the test data
				  //read plaintext and write encoded_text
				  // for(int i=0;i<encoder_list.length;i++)
				  // {
				
				    String cipher_text=get_cipher_text(encoder_list[cipher_id],pre_line);
					String result=encoder_list[cipher_id]+"\t"+cipher_text+"\n";
					out.write(result);
					//System.out.println(count);
				//	count++;
				//   }
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
