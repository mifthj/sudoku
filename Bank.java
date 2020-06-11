package FinalProject;

import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;

class Sudoku 
{ 
public static boolean Check(int[][] board, 
							 int row, int col, 
							 int num)
	{ 
	//check the number in row
	for (int d = 0; d < board.length; d++){ 
		 
		if (board[row][d] == num)
		{
			return false; 
		}
	}
	
	//check the number in column
	for (int r = 0; r < board.length; r++) 
	{  
		if (board[r][col] == num) 
		{ 
			return false; 
		} 
	} 

	//check the number in 3x3 box 
	int sqrt = (int) Math.sqrt(board.length); 
	int boxRowStart = row - row % sqrt; 
	int boxColStart = col - col % sqrt; 

	for (int r = boxRowStart; 
			r < boxRowStart + sqrt; r++) 
	{ 
		for (int d = boxColStart; 
				d < boxColStart + sqrt; d++) 
		{ 
			if (board[r][d] == num) 
			{ 
				return false; 
			} 
		} 
	} 
	
	return true; 
} 

public static boolean solveSudoku(int[][] board, int n) 
{ 
	int row = -1; 
	int col = -1; 
	boolean isEmpty = true; 
	for (int i = 0; i < n; i++) 
	{ 
		for (int j = 0; j < n; j++) 
		{ 
			if (board[i][j] == 0) 
			{ 
				row = i; 
				col = j; 
				
				isEmpty = false; 
				break; 
			} 
		} 
		if (!isEmpty) 
		{ 
			break; 
		} 
	} 
 
	if (isEmpty) 
	{ 
		return true; 
	} 
 
	for (int num = 2; num <= Math.pow(2, n); num = num*2) 
	{ 
		if (Check(board, row, col, num)) 
		{ 
			board[row][col] = num; 
			if (solveSudoku(board, n)) 
			{  
				return true; 
			} 
			else
			{ 
				board[row][col] = 0; 
			} 
		} 
	} 
	return false; 
} 

public static void print(int[][] board, int N) 
{  
	for (int r = 0; r < N; r++) 
	{ 
		for (int d = 0; d < N; d++) 
		{ 
			if(board[r][d] == 0)
			{
				System.out.print("_");
			}
			else
			{
				System.out.print(board[r][d]);
			}
			System.out.print("\t"); 
		} 
		System.out.print("\n"); 	
	} 
} 


public static void main(String args[]) 
{ 
	Scanner scan = new Scanner(System.in);
	
	//other puzzles in Bank class, you can copy from there :)
	int[][] board = new int[][] 
	{ 
		{0, 0, 64, 16, 256, 2, 8, 0, 0}, 
		{0, 4, 0, 0, 0, 0, 0, 16, 0}, 
		{128, 0, 0, 0, 0, 0, 0, 0, 512}, 
		{256, 0, 0, 0, 512, 0, 0, 0, 16}, 
		{64, 0, 0, 8, 16, 4, 0, 0, 2}, 
		{32, 0, 0, 0, 64, 0, 0, 0, 4}, 
		{8, 0, 0, 0, 0, 0, 0, 0, 32}, 
		{0, 512, 0, 0, 0, 0, 0, 128, 0}, 
		{0, 0, 32, 128, 2, 64, 4, 0, 0}
	};
//	int[][] board2 = new int[][]
//	{
//		
//	};
	int N = board.length;
	int[][] answer = new int[N][N];
	
	boolean flag = true;
	
	print(board, N);
	int count = 1;
	while(flag)
	{
		if(solveSudoku(board, N))
		{
			if(count == 1)
			{
				System.out.println("\n\nTHIS IS HOW TO PLAY");
				System.out.println("1. This game is Sudoku");
				System.out.println("2. This Sudoku is different with another Sudoku");
				System.out.println("3. What makes this game different is, the number"
									+ " started from 2^1 until 2^9");
				System.out.println();
				System.out.println("\nStart!\n");
			}
			else if (count == 5)
			{
				System.out.println("\nLook like you are not ready to solve this :)");
				System.out.println("I'll give you the answer!\n");
				print(board, N);
				flag = false;
			}
			else
			{
				System.out.println("\nretry!\n");
			}
		
			for(int i = 0; i < N; i++)
			{
				for (int j = 0; j< N; j++)
				{
					answer[i][j] = scan.nextInt();
				}
			}
			if (Arrays.deepEquals(answer, board)) {
				flag = false;
				System.out.println("Congratulations! You Win!");
				System.out.println("Your Score is : " + (100-count)*81);
			}
		}
		else
		{
			System.out.println("No Solution\n");
			flag = false;
		}
		count++;
	}
} 
}