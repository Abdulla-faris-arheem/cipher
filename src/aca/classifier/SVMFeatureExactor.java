package aca.classifier;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.io.*;

import aca.main.encoder;

public class SVMFeatureExactor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length<4)
		{
			System.err.println("Argument error");
			System.err.println("Argument: input_file output_file uni_gram_file bi_tri_gram");
			System.exit(1);
		}
		SVMExtractor se=new SVMExtractor(args[2],args[3]);
		  try
	        {
	        	 BufferedReader bf=new BufferedReader(new FileReader(args[0]));
	             String line;
	        	BufferedWriter bw=new BufferedWriter(new FileWriter(args[1]));
	        	int count=0;
	           while((line=bf.readLine())!=null)
	           {
	        	 //  System.out.println(count);
	        	   String[] parts=line.trim().split("\t");
	        	   if(parts.length!=2)
	        	   {
	        		   System.err.println("Erroneous format");
	        		   System.exit(1);
	        	   }
	        	   String tag=parts[0];
	     //   	   c.print_test_data(tag,parts[1]);
	        	   SVMFeature f=se.extract_feature(parts[1]);
	        	   ArrayList<Double> feat_vec=se.build_feature_vec(f);
	        	   se.print_feature(tag, feat_vec, bw);
	        	   count++;
	        	   System.err.println(count);
	           }
	           bw.close();
	           bf.close();
	           
	        }
	        catch(Exception e)
	        {
	        	System.err.println(e.getMessage());
	        }
		
		
		
		
	}

}
