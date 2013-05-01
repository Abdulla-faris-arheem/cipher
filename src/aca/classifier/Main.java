package aca.classifier;

import java.io.*;
import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length<3)
		{
			System.err.println("Argument error");
			System.err.println("Argument: input_file output_file uni_gram_file");
			System.exit(1);
		}
        SVMClassifier c=new SVMClassifier();
       
        try
        {
        	 c.read_uni_prob(args[2]);
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
        	   SVMFeature f=c.extract_feature(parts[1]);
        	   ArrayList<Double> feat_vec=c.build_feature_vec(f);
        	   c.print_training(tag, feat_vec, bw);
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
