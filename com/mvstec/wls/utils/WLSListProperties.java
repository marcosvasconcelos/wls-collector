package com.mvstec.wls.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * /**
 * 
 * @author Marcos Vinícius Vasconcelos Silva
 * @version 1.1
 * @since 10 January 2017 <h1>WLSListProperties class to Weblogic Scrpt
 *        Interface</h1>
 * @category Class used to create a server list to connect from remote server.
 * 
 */


public class WLSListProperties {

	String username;
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> setWLSList() throws Exception {

		Properties prop = new Properties();
		InputStream fileWMLIST = null;
		String[] srvList;
		List<String> srvProps = new ArrayList<String>();
		try {

			fileWMLIST = new FileInputStream(
					"./wmlist.properties");

			// Load a properties from fileWMLIST

			prop.load(fileWMLIST);
			
			// Creating a list for multiple servers
			srvList = prop.getProperty("server").split(",");

			for (int i = 0; i < srvList.length; i++) {

				srvProps.add(srvList[i]);

			}

			// Set Username and Password from fileWMLIST

			setUsername(prop.getProperty("username"));
			setPassword(prop.getProperty("password"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (fileWMLIST != null) {
				try {
					fileWMLIST.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return srvProps;
	}

}
