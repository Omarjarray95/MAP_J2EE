package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server 
{
	public static void main(String[] args) 
	{
		ServerSocket SS;
		Socket S;
		final BufferedReader BR;
		final PrintWriter PW;
		final Scanner Sc = new Scanner(System.in);
		
		try 
		{
			SS = new ServerSocket(5000);
			S = SS.accept();
			PW = new PrintWriter(S.getOutputStream());
			BR = new BufferedReader(new InputStreamReader(S.getInputStream()));
			
			Thread TS = new Thread(new Runnable() 
			{
				String M;
				@Override
				public void run() 
				{
					while(true)
					{
						M = Sc.next();
						PW.println(M);
						PW.flush();
					}
				}
			});
			
			TS.start();
			
			Thread TR = new Thread(new Runnable() 
			{
				String M;
				@Override
				public void run() 
				{
					while(true)
					{
						try 
						{
							M = BR.readLine();
							System.out.println("Client : " + M);
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
					}
				}
			});
			
			TR.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
