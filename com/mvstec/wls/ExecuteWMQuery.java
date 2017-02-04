package com.mvstec.wls;

import com.mvstec.wls.utils.WLSListProperties;



/**
 * @author Marcos Vinicius Vasconcelos Silva
 * @version 1.0
 * @since 19 December 2016
 * <h1> ExecuteWMQuery class to Weblogic Scrpt Interface </h1>
 *  
 */

public class ExecuteWMQuery {
	// static InteractiveInterpreter interpreter = null;
	
	//static String server = "srv02.mvstec.com:7003";
	//static String username = "weblogic";
	//static String password = "teste@123";
	
	//static String server = "5.16.37.11";
	//static String port = "7001";
	//static String username = "netoi";
	//static String password = "97433086";


	public static void main(String[] args) {

		WorkManagerQuery wmq = new WorkManagerQuery();
		WLSListProperties srvList = new WLSListProperties();
		ConnectWLSTUtil connWLSTUtil = new ConnectWLSTUtil();
		try {
			for (String srv : srvList.setWLSList()) {
				System.out.println(srv);
				
				if (wmq.connServerWLST(srv, srvList.getUsername(), srvList.getPassword())== true){
					wmq.listGlobalWorkManagers();
					wmq.listMinThreadsConstraint();
					wmq.listMaxThreadsConstraint();
					wmq.listRequestClass();
					connWLSTUtil.disconnectWLST();
				} else System.out.println("Server is not runing!");
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//wmq.connServerWLST(server, username, password);
		//wmq.listGlobalWorkManagers();
		//wmq.listMinThreadsConstraint();
		//wmq.listMaxThreadsConstraint();
		//wmq.listRequestClass();
		//connWLSTUtil.disconnectWLST();

	}

}
