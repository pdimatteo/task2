package com.pdm.task2;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ConnectionManager {

	private static final String USER = "star3";
    private static final String PASSWORD = "usi_star3";
    private static final String HOST = "195.176.181.126";
    private static int PORT = 22;
	
    private JSch jsch;
    private Session session;
    
    
	public void init() {
    	jsch = new JSch();
    }
    
    public void startSession() throws JSchException {
    	session = jsch.getSession(USER, HOST, PORT);
        session.setPassword(PASSWORD);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
    }
   
    public Session getSession() {
		return session;
	}
 
}
