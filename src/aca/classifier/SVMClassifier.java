package aca.classifier;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.io.*;
import main.encoder;

public class SVMClassifier implements Classifier {
	
	public double compute_ic(String text)
	{
		HashMap<Character,Integer> h_map=new HashMap<Character,Integer>();
		String text_l=text.toLowerCase();
		for(char c: text_l.toCharArray())
		{
			Character cur_c=Character.valueOf(c);
			if(h_map.containsKey(cur_c))
			{
				h_map.put(cur_c, h_map.get(cur_c)+1);
			}
			else
			{
				h_map.put(cur_c, 1);
			}
		}
		int total_len=text.length();
		if(total_len<=1)
		{
			//denominator=0
			System.err.println("The total length is less than 2. Unable to compute ic");
		}
		int numerator=0;
		for(Entry<Character, Integer> entry: h_map.entrySet())
		{
			int cur_num=entry.getValue();
			if(cur_num==1)
			{
				continue;
			}
			else
			{
				numerator+=cur_num*(cur_num-1);
			}
		}
		int denominator=total_len*(total_len);
		return (double)numerator/(double)denominator;
	/*	int numerator=0;
		int text_len=text.length();
		int[] char_cnt=new int[26];
		for(char c:text.toLowerCase().toCharArray())
		{
			char_cnt[c-'a']+=1;
		}
		for(int i=0;i<26;i++)
		{
		   if(char_cnt[i]>1)
		   {
			   numerator+=char_cnt[i]*(char_cnt[i]-1);
		   }
		}
		if(numerator==0)
			return 0.0;
		double denominator=(double)(text_len*(text_len-1))/(double)26;
		if(denominator==0)
		{
			System.err.println("Error in computing the ic for "+text);
		}
		return (double)numerator/denominator;*/
		
	}
	
	public boolean contain_j(String text)
	{
		for(char c:text.toCharArray())
		{
			if(c=='j')
			{
				return true;
			}
		}
		return false;
	}
	
	public HashMap<Character,Integer> get_freq(String text)
	{
		HashMap<Character,Integer> map=new HashMap<Character,Integer>();
		for(char c:text.toLowerCase().toCharArray())
		{
			if(map.containsKey(c))
			{
				int cur_feq=map.get(c);
				map.put(c, cur_feq+1);
			}
			else
			{
				map.put(c, 1);
			}
		}
		return map;
	}
	
	public void read_uni_prob(String prob_file)
	{
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(prob_file));
			String line;
			while((line=br.readLine())!=null)
			{
				if(line.startsWith("#"))
					continue;
				else
				{
					String[] parts=line.trim().split("\t");
					assert(parts.length==2);
					Character cur=Character.valueOf(parts[0].charAt(0));
					Double value=Double.parseDouble(parts[1]);
					uni_probs.put(cur, value);
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	private HashMap<Character,Double> uni_probs=new HashMap<Character,Double>();
	
	private boolean all_number(String text)
	{
		for(char c:text.toCharArray())
		{
			if(c<'0' || c>'9')
				return false;
		}
		return true;
	}
    
	public SVMFeature extract_feature(String text)
	{
		SVMFeature f=new SVMFeature();
		if(contain_j(text))
		{
			f.has_j=true;
		}
		else
		{
			f.has_j=false;
		}
		if(all_number(text))
		{
			f.all_num=true;
		}
		else
		{
			f.all_num=false;
		}
		if(text.contains("#"))
		{
			f.contain_star=true;
		}
		else
		{
			f.contain_star=false;
		}
		double ic=compute_ic(text);
		f.ic=ic;
		// HashMap<Character,Integer> frequencies=
		f.frequencies=get_freq(text);
		f.length=text.length();
		if(text.length()%2==0)
		{
			f.length_even=true;
		}
		else
		{
			f.length_even=false;
		}
		f.uni_prob=compute_uni_prob(text);
		return f;
	}
	
	public double compute_uni_prob(String text)
	{
		String text_low=text.toLowerCase();
		//double result=1.0;
		double result=0.0;
		for(char c:text_low.toCharArray())
		{
			//result*=uni_probs.get(c);
			if(c<'a' || c>'z')
			{
				continue;
			}
			result+=Math.log(uni_probs.get(Character.valueOf(c)));
		}
		result/=text.length();
		return result;
	}
	
	public ArrayList<Double> build_feature_vec(SVMFeature f)
	{
		ArrayList<Double> vec_feat=new ArrayList<Double>();
		if(f.has_j)
		{
			vec_feat.add(1.0);
			vec_feat.add(0.0);
		}
		else
		{
			vec_feat.add(0.0);
			vec_feat.add(1.0);
		}
		if(f.length_even)
		{
			vec_feat.add(1.0);
			vec_feat.add(0.0);
		}
		else
		{
			vec_feat.add(0.0);
			vec_feat.add(1.0);
		}
		if(f.all_num)
		{
			vec_feat.add(1.0);
			vec_feat.add(0.0);
		}
		else
		{
			vec_feat.add(0.0);
			vec_feat.add(1.0);
		}
		if(f.contain_star)
		{
			vec_feat.add(1.0);
			vec_feat.add(0.0);
		}
		else
		{
			vec_feat.add(0.0);
			vec_feat.add(1.0);
		}
		vec_feat.add(f.ic);
		//vec_feat.add((double)f.length);
		for (int i=0;i<26;i++)
		{
			char cur_char=(char)('a'+i);
			if(f.frequencies.containsKey(cur_char))
			{
				int c_freq=f.frequencies.get(cur_char);
			    vec_feat.add((double)c_freq/(double)f.length);//unigram probabilities
			}
			else
			{
				vec_feat.add(0.0);
			}
		}
		//0304 add the unigram prob
		vec_feat.add(f.uni_prob);
		return vec_feat;
	}
	
	public ArrayList<Double> scale_feat(ArrayList<Double> f)
	{
		ArrayList<Double> result=new ArrayList<Double>();
		double highest=0.0;
		for(int i=0;i<f.size();i++)
		{
			if(f.get(i)>highest)
			{
				highest=f.get(i);
			}
		}
		for(int i=0;i<f.size();i++)
		{
			result.add(f.get(i)/highest);
		}
		return result;
	}
	
	public void print_training(String tag,ArrayList<Double> features, BufferedWriter bw)
	{
		//for(int i=0;i<features.size();i++)
		//{
		  StringBuilder sb=new StringBuilder();
		  try
		  {
		    for(int i=0;i<features.size();i++)
		    {
			   sb.append(Integer.toString(i+1)+":"+Double.toString(features.get(i))+" ");
		    }
		  //  if (encoder.encoder_list)
		    //convert tag to id
		    int tag_id=encoder.get_encoder_index(tag);
		    if(tag_id==-1)
		    {
		    	System.err.println("Invalid tag");
		    }
		    String to_print=Integer.toString(tag_id)+" "+sb.toString().trim();
		    bw.write(to_print+"\n");
		  }
		  catch(Exception e)
		  {
			  System.err.println(e.getMessage());
		  }
		  //bw.write(sb.toString());
		  //bw.write("\n");
		//}
		
		
		
	//	if (encoder.encoder_list.)
		
		
	}
	
	/*public void print_test(String tag,String encipher_text)
	{
		
	}*/
	
	//digraph incidence of coincidence
/*	public double get_dic(String text)
	{
		int text_len=text.length();
		if(text_len%2!=0)
		{
			return 0.0;
		}
		HashMap<String,Integer> di_count=new HashMap<String,Integer>();
		for(int i=0;i<text_len;i+=2)
		{
			String digraph=text.substring(i,i+2);
			if(di_count.containsKey(digraph))
			{
				int cur_cnt=di_count.get(digraph);
			    di_count.put(digraph, cur_cnt+1);
			}
			else
			{
				di_count.put(digraph,1);
			}
		}
		int numerator=0;
		for(String k:di_count.keySet())
		{
			int cnt=di_count.get(k);
			if(cnt>1)
			{
				numerator+=cnt*(cnt-1);
			}
		}
		if(numerator==0)
			return 0.0;
		double denominator=(double)(text_len)*(double)(text_len-1)/(double)(text_len/2);
		return (double)numerator/denominator;
		
	}*/
	
	
	
}
