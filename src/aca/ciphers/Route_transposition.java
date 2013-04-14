package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

public class Route_transposition implements Cipher {
	
	public Route_transposition()
	{
		Random r=new Random();
		col=2+r.nextInt(5);
	}
	
	public Route_transposition(int c)
	{
		assert(c>=2);
		col=c;
	}
	
	private int col;
	
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
		boolean up=false;
		for(int i=off;i<total_len-off;i++)
		{
			result[cur_row][cur_col]=p.charAt(i);
	
		switch(method)
		{
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			if(cur_row==row-1)
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
			}
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
			}		
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
	}
		for(int i=0;i<off;i++)
		{
			result[cur_row][cur_col]=p.charAt(i);
			switch(method)
			{
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				if(cur_row==row-1)
				{
					if(cur_col%2==0)
					{
						cur_col++;
						up=true;
					}
					else
					{
						cur_col++;
						cur_row--;
						up=true;
					}
				}
				else if(cur_col==column-1)
				{
					if(cur_row%2==0)
					{
						cur_row++;
						up=false;
					}
					else
					{
						cur_row++;
						cur_col--;
					}
				}
				else if(cur_col==0)
				{
				   if(cur_row==0)
				   {
					   cur_col++;
					   up=false;
				   }
				   else if(cur_row%2==1)
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
					if(cur_col%2==0)
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
				}		
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
		}
		return result;
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
		switch(method)
		{
		case 0:
			break;
		case 1:
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
				sb.append(block[start_row][i]);
			}
			for(int i=start_row+1;i<=end_row;i++)
			{
				sb.append(block[i][end_col]);
			}
			for(int i=end_col-1;i>=start_col;i--)
			{
				sb.append(block[end_row][i]);
			}
			for(int i=end_row-1;i>=start_row+1;i--)
			{
				sb.append(block[i][start_col]);
			}
			sb.append(sprial_inward(block,start_row+1,end_row-1,start_col+1,end_col-1));
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
}
