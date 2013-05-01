package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

public class Route_transposition implements Cipher {
	
	public Route_transposition()
	{
		Random r=new Random();
		col=2+r.nextInt(5);
		System.err.println(col);
	}
	
	public Route_transposition(int c)
	{
		assert(c>=2);
		col=c;
	}
	
	public Route_transposition(int method,int c)
	{
		col=c;
		this.method=method;
	}
	
	private int col;
	private int method;
	private int offset;
	
	public String encode(String plain)
	 {
		char[][] block=build_block(plain,col,5,0);
		String result=read_block(block,7);
		return result.toUpperCase();
	 }
	
	
	/**
	 * Build a block from a string using some method
	 * 
	 * @param p the source string
	 * @param column the block column number
	 * @param method method id. 0 for horizontal, 1 for vertical, 2 for alternating horizontal,
	 * 3 for alternating vertical, 4 for diagonal, 5 for alternating diagonal, 6 for clockwise
	 * inward spiral, 7 for counterclockwise inward spiral, 8 for clockwise outward spiral, 9 for
	 * counterclockwise outward spiral
	 * @param offset the starting position
	 * @return a two-dimensional array that contains the block built
	 */
	public static char[][] build_block(String p,int column,int method,int offset)
	{
		int total_len=p.length();
		int row=total_len/column;
		if(total_len%column!=0)
		{
			row+=1;
		}
		char[][] result=new char[row][column];
		int off=offset%total_len;
		int cur_row=0;
		int cur_col=0;
	//	boolean up=false;
		String new_p=p.substring(off)+p.substring(0, off);
		//for(int i=off;i<total_len-off;i++)
		//for(int i=0;i<new_p.length();i++)
		//{
	//		result[cur_row][cur_col]=p.charAt(i);
	
		 switch(method)
		{
		case 0:
			result=Incomp_column.build_block(new_p, row, column, 0);
			break;
		case 1:
			result=Incomp_column.build_block(new_p, row, column, 1);
			break;
		case 2:
			build_alternate_block(result,new_p,0,cur_row,cur_col,true,0);
			break;
		case 3:
			build_alternate_block(result,new_p,0,cur_row,cur_col,true,1);
			break;
		case 4:
			//diagonal
			build_diagonal_block(result,new_p,false);
		/*	if(row==1)
			{
				//horizontal
				result=Incomp_column.build_block(new_p, row, column, 1);
			}
			else if(column==1)
			{
				//vertical
				result=Incomp_column.build_block(new_p, row, column, 1);
			}
			else
			{
			  int start_row=0;
			  for(int i=0;i<new_p.length();i++)
			  {
				 result[cur_row][cur_col]=p.charAt(i);
				 if(cur_row==0 || cur_col==column-1)
				 {
					cur_col=0;
					start_row++;
					cur_row=start_row;
				 }
				 else
				 {
					 cur_row--;
					 cur_col++;
				 }
			   }
			}*/
			break;
		case 5:
			build_diagonal_block(result,new_p,true);
	//		build_alternate_block(result,new_p,0,cur_row,cur_col,true,2);
		/*	if(cur_row==row-1)
			{
				if(!up)
				{
					cur_col++;
					up=true;
				}
				else
				{
					cur_col++;
					cur_row--;
				}
				/*if(cur_col%2==0)
				{
					cur_col++;
					up=true;
				}
				else
				{
					cur_col++;
					cur_row--;
					up=true;
				}*/
		/*	}
			else if(cur_col==column-1)
			{
				if(!up)
				{
					cur_row++;
					cur_col--;
				}
				else
				{
					cur_row++;
					up=false;
				}
				
			}
			else if(cur_col==0)
			{
			   if(cur_row==0)
			   {
				   cur_col++;
				   up=false;
			   }
			   else if(!up)
			   {
				   cur_row++;
				   up=true;
			   }
			   else
			   {
				   cur_row--;
				   cur_col++;
			   }
			}
			
			else if(cur_row==0)
			{
				if(up)
				{
					cur_col++;
					up=false;
				}
				else
				{
					cur_col--;
					cur_row++;
					up=false;
				}
			}
			
			else if(up)
			{
				cur_col++;
				cur_row--;
			}
			else
			{
				cur_col--;
				cur_row++;
			}*/		
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		default:
			System.err.println("Unrecognized method id:"+method);
			break;
		}
	//}
		return result;
	}
	
	public static void build_diagonal_block(char[][] result, String p, boolean alter)
	{
		int row=result.length;
		int col=result[0].length;
		int cur_pos=0;
		for(int i=0;i<row+col;i++)
		{
			if(cur_pos>=p.length())
				break;
			if(alter)
			{
				if(i%2==0)
				{
					 int j=i<row?i:row-1;
					 int k=i-j;
					 while(j>=0 && k<col)
					 {
						 result[j--][k++]=p.charAt(cur_pos);
						 cur_pos++;
						 if(cur_pos>=p.length())
								break;
					 }
				  
				}
				else
				{
					 int j=i<col?i:col-1;
					 int k=i-j;
					 while(k<row && j>=0)
					 {
					     result[k++][j--]=p.charAt(cur_pos);
					     cur_pos++;
					     if(cur_pos>=p.length())
								break;
					 }
				}
			}
			else
			{
				 int j=i<col?i:col-1;
				 int k=i-j;
				 while(k<row && j>=0)
				 {
				     result[k++][j--]=p.charAt(cur_pos);
				     cur_pos++;
				 }
			}
		}
	}
	
	/**
	 * Read a rectangle block
	 * @param block block to be read
	 * @param method method id. 0 for horizontal, 1 for vertical, 2 for alternating horizontal,
	 * 3 for alternating vertical, 4 for diagonal, 5 for alternating diagonal, 6 for clockwise
	 * inward spiral, 7 for counterclockwise inward spiral, 8 for clockwise outward spiral, 9 for
	 * counterclockwise outward spiral
	 * @return the result string
	 */
	public static String read_block(char[][] block, int method)
	{
		StringBuilder sb=new StringBuilder();
		int row=block.length;
		int col=block[0].length;
		switch(method)
		{
		case 0:
			for(int i=0;i<row;i++)
			{
				for(int j=0;j<col;j++)
				{
					if(block[i][j]!='\0')
						sb.append(block[i][j]);
				}
			}
			break;
		case 1:
			for(int i=0;i<col;i++)
			{
				for(int j=0;j<row;j++)
				{
					if(block[j][i]!='\0')
						sb.append(block[i][j]);
				}
			}
			break;
		case 2:
			
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:		
			break;
		case 6:
			break;
		case 7:
			int total_row=block.length;
			int total_col=block[0].length;
			sb.append(sprial_inward(block,0,total_row-1,0,total_col-1));
		//	boolean[][] visited=new boolean[total_row][total_col];
		//	int cur_row=0;
		//	int cur_col=0;
		//	boolean right=true;
		/*	while(true)
			{
				if(block[cur_row][cur_col]!='\0')
				{
					sb.append(block[cur_row][cur_col]);
					visited[cur_row][cur_col]=true;
				}
				//update cur_row and cur_col
				if(cur_col==total_col-1)
				{
					
				}
			}*/
			break;
		case 8:
			break;
		case 9:
			break;
		default:
			System.err.println("Unrecognized method id:"+method);
			break;
		}
		return sb.toString();
	}
	
	public static String sprial_inward(char[][] block,int start_row,int end_row,int start_col,int end_col)
	{
		if(start_row>end_row || start_col>end_col)
		{
			return "";
		}
		StringBuilder sb=new StringBuilder();
		if(start_row==end_row)
		{
			for(int i=start_col;i<=end_col;i++)
			{
			  if(block[start_row][i]!='\0')
			  {
				sb.append(block[start_row][i]);
			  }
			}
		}
		else if(start_col==end_col)
		{
			for(int i=start_row;i<=end_row;i++)
			{
				if(block[i][start_col]!='\0')
				  {
					sb.append(block[i][start_col]);
				  }
			}
		}
		else
		{
			//peel off the outer layer
			for(int i=start_col;i<=end_col;i++)
			{
				if(block[start_row][i]!='\0')
				{
				  sb.append(block[start_row][i]);
				}
			}
			for(int i=start_row+1;i<=end_row;i++)
			{
				if(block[i][end_col]!='\0')
				{
				  sb.append(block[i][end_col]);
				}
			}
			for(int i=end_col-1;i>=start_col;i--)
			{
				if(block[end_row][i]!='\0')
				{
				  sb.append(block[end_row][i]);
				}
			}
			for(int i=end_row-1;i>=start_row+1;i--)
			{
				if(block[i][start_col]!='\0')
				{
				  sb.append(block[i][start_col]);
				}
			}
			sb.append(sprial_inward(block,start_row+1,end_row-1,start_col+1,end_col-1));
		}
		return sb.toString();
	}
	
	public static void build_alternate_block(char[][] result,String p, int start_pos,int cur_row,int cur_col,boolean direction,int method)
	{
		int row=result.length;
		int col=result[0].length;
		if(start_pos>=p.length())
			return;
		if(cur_row>row || (cur_row==row && cur_col==col) || cur_col>col || cur_col<0 || cur_row<0)
			return;
		int end_p;
		boolean new_direction=direction^true;
		switch(method)
		{
		case 0:
			//horizontal
				end_p=start_pos+col>p.length()?p.length():start_pos+col;
				for(int i=start_pos;i<end_p;i++)
				{
					result[cur_row][cur_col]=p.charAt(i);
					if(direction)
					{
					  cur_col++;
					}
					else
					{
						cur_col--;
					}
				}
				
				build_alternate_block(result,p,end_p,cur_row+1,cur_col,new_direction,method);
				break;
		case 1:
			//vertical
			   end_p=start_pos+row>p.length()?p.length():start_pos+row;
			   for(int i=start_pos;i<end_p;i++)
				{
					result[cur_row][cur_col]=p.charAt(i);
					if(direction)
					{
					  cur_row++;
					}
					else
					{
						cur_row--;
					}
				}
				build_alternate_block(result,p,end_p,cur_row,cur_col+1,new_direction,method);
				break;
			
		
		/*case 2:
			//diagonal
			if(row==1)
			{
				build_alternate_block(result,p,start_pos,cur_row,cur_col,direction,0);
			}
			else if(col==1)
			{
				build_alternate_block(result,p,start_pos,cur_row,cur_col,direction,1);
			}
		/*	else
			{
				int str_pos=0;
				for(int i=0;i<row+col;i++)
				{
					if(i%2==0)
					{
						int j=i<row?i:row-1;
						int k=i-j;
						while(j>=0 && k<=col)
						{
							result[j++][k--]=p.charAt(str_pos);
						}
					}
				}
			
			}*/
		
	}
}
	 
	
	public static String read_alternate_block(char[][] result,String p, int start_pos,int cur_row,int cur_col,boolean direction,int method)
	{
		StringBuilder sb=new StringBuilder();
		int row=result.length;
		int col=result[0].length;
		if(start_pos>=p.length())
			return "";
		if(cur_row>row || (cur_row==row && cur_col==col) || cur_col>col || cur_col<0 || cur_row<0)
			return "";
		int end_p;
		boolean new_direction=direction^true;
		switch(method)
		{
		case 0:
			//horizontal
				end_p=start_pos+col>p.length()?p.length():start_pos+col;
				for(int i=start_pos;i<end_p;i++)
				{
					if(result[cur_row][cur_col]!='\0')
					{
						sb.append(result[cur_row][cur_col]);
					}
					//result[cur_row][cur_col]=p.charAt(i);
					if(direction)
					{
					  cur_col++;
					}
					else
					{
						cur_col--;
					}
				}
				
				sb.append(read_alternate_block(result,p,end_p,cur_row+1,cur_col,new_direction,method));
				break;
		case 1:
			//vertical
			   end_p=start_pos+row>p.length()?p.length():start_pos+row;
			   for(int i=start_pos;i<end_p;i++)
				{
				   if(result[cur_row][cur_col]!='\0')
					{
						sb.append(result[cur_row][cur_col]);
					}
					//result[cur_row][cur_col]=p.charAt(i);
					if(direction)
					{
					  cur_row++;
					}
					else
					{
						cur_row--;
					}
				}
				sb.append(read_alternate_block(result,p,end_p,cur_row,cur_col+1,new_direction,method));
				break;
			
		
		case 2:
			//diagonal
			if(row==1)
			{
				return read_alternate_block(result,p,start_pos,cur_row,cur_col,direction,0);
				//build_alternate_block(result,p,start_pos,cur_row,cur_col,direction,0);
			}
			else if(col==1)
			{
				return read_alternate_block(result,p,start_pos,cur_row,cur_col,direction,1);
				//build_alternate_block(result,p,start_pos,cur_row,cur_col,direction,1);
			}
			else
			{
				int diag=cur_row<cur_col?cur_row:cur_col;
				 end_p=start_pos+diag+1>p.length()?p.length():start_pos+diag+1;
				 for(int i=start_pos;i<end_p;i++)
				 {
					//result[cur_row][cur_col]=p.charAt(i);
					if(direction)
					{
						cur_col++;
						cur_row--;
					}
					else
					{
						cur_row++;
						cur_col--;
					}
				}
				if(direction)
				{
					sb.append(read_alternate_block(result,p,end_p,cur_row,cur_col+1,new_direction,method));
				}
				else
				{
					sb.append(read_alternate_block(result,p,end_p,cur_row+1,cur_col,new_direction,method));
				}
				 
			}
			
		
	}
		return sb.toString();
}
	    /*
	     * Decode the cipher text.
	     */
	    public String decode(String cipher)
	    {
	    	return null;
	    }
	    
	    public boolean key_need()
	    {
	    	return false;
	    }
	    
	    public int get_key_num()
	    {
	    	return 0;
	    }
	    
	    public ArrayList<Integer> get_key_len()
	    {
	    	return null;
	    }
	    
	    public int process_id()
		{
			return 0;
		}
}
