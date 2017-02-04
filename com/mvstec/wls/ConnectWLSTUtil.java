package com.mvstec.wls;

public class ConnectWLSTUtil {

	public String connectWLST(String server, String username, String password) {

		StringBuffer stbConn = new StringBuffer();
		stbConn.append("connect('" + username + "','" + password + "','t3://"
				+ server + "')");
		System.out
				.println("'" + username + "," + password + "," + server + "'");
		return stbConn.toString();

	}

	public String disconnectWLST() {
		StringBuffer stbDisconn = new StringBuffer();
		stbDisconn.append("disconnect(force='true')\n");
		return stbDisconn.toString();

	}
}
