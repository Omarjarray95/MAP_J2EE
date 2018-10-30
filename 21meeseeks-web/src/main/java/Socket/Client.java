package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{

	public static void main(String[] args) 
	{
		Socket S;
		final BufferedReader BR;
		final PrintWriter PW;
		final Scanner Sc = new Scanner(System.in);
		
		try 
		{
			S = new Socket("127.0.0.1", 5000);
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
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						System.out.println("Serveur : " + M);
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
