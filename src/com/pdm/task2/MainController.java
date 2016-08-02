package com.pdm.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.pdm.task2.ConnectionManager;

public class MainController {

private static int sleepTime = 1000; 
	
public static void main(String[] args) throws InterruptedException {
		
	String remoteFile = "/home/star3/tasks/task1/data.txt";

	ConnectionManager cm = new ConnectionManager();
	Session session = null;
	ChannelSftp sftpChannel = null;
        
	cm.init();
		
	try {
		cm.startSession();
		session = cm.getSession();
	} catch (JSchException e) {
		System.out.println("An error occours while trying to connect");
	}

	Scanner reader = new Scanner(System.in); 
	System.out.println("Enter sleep time (ms): ");
	sleepTime = reader.nextInt();
	
	while(true) {
		if(session != null) {
			try {
				sftpChannel = (ChannelSftp) session.openChannel("sftp");
				sftpChannel.connect();
				
				InputStream out = null;
				out = sftpChannel.get(remoteFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(out));
		        
				String line;
				while ((line = br.readLine()) != null)
					System.out.println(line);
		        	br.close();
				} catch (JSchException | SftpException e) {
					System.out.println("An error occours while trying to create sftp channel");
				} catch (IOException e) {
					System.out.println("IO exception");	
				}
			}	
			Thread.sleep(sleepTime);
		}
	}

}
