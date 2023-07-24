package com.inops.visitorpass.service;

public interface IExecutorEngine {

	public String start();
	
	public String stop();
	
	public String list();
	
	public String restart();
	
	public String cancel();
}
