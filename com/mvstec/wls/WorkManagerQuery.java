package com.mvstec.wls;

import java.util.ArrayList;
import java.util.List;

import org.python.core.PyInteger;
import org.python.core.PyLong;
import org.python.core.PyObject;

import weblogic.management.scripting.utils.WLSTInterpreter;

/**
 * /**
 * 
 * @author Marcos Vinícius Vasconcelos Silva
 * @version 1.0
 * @since 19 December 2016 <h1>WorkManagerQuery class to Weblogic Scrpt
 *        Interface</h1>
 * 
 */

public class WorkManagerQuery {

	WLSTInterpreter interpreter = new WLSTInterpreter();
	ConnectWLSTUtil connWLSTUtil = new ConnectWLSTUtil();

	// public WorkManagerQuery() {

	// TODO Auto-generated constructor stub
	// interpreter = new WLSTInterpreter();
	// connWLSTUtil = new ConnectWLSTUtil();

	// }

	/**
	 * <strong>Attributes to define WLST executions</strong> The attributes
	 * bellow are used to define: -- What method will be used in WLST get; --
	 * What level and What are MBeans that will be search in WLS Interface. --
	 * Important remember that all attributes should be classified as STATIC and
	 * FINAL.
	 */

	static final String completedRequests = "').getCompletedRequests()";
	static final String pendingRequests = "').getPendingRequests()";
	static final String stuckThreadCount = "').getStuckThreadCount()";
	static final String executingRequests = "').getExecutingRequests()";
	static final String deferredRequests = "').getDeferredRequests()";
	static final String completedCount = "').getCompletedCount()"; // Used only
																	// in
																	// RequestClassRuntimes
	static final String pendingRequestCount = "').getPendingRequestCount()"; // Used
																				// only
																				// in
																				// RequestClassRuntimes
	static final String totalThreadUse = "').getTotalThreadUse()";// Used only
																	// in
																	// RequestClassRuntimes
	static final String mbeanGet = "getMBean('";
	static final String srvRuntime = "serverRuntime()\n";
	static final String WM = "cd('WorkManagerRuntimes')\n";
	static final String seflTUNING_MinThreads = "cd('MinThreadsConstraintRuntimes')\n";
	static final String seflTUNING_MaxThreads = "cd('MaxThreadsConstraintRuntimes')\n";
	static final String seflTUNING_RequestClass = "cd('RequestClassRuntimes')\n";

	static final String lista = "ls()\n";

	PyLong compReq;
	PyInteger pendReq;
	PyInteger execReq;
	PyInteger difReq;
	PyLong totalThreads;
	long getComReq;
	long getDifReq;
	Integer getPendReq;
	Integer getExecReq;
	long getTotalThreads;
	boolean isConnected;

	public boolean connServerWLST(String server, String username,
			String password) {
		try {
			interpreter.exec(connWLSTUtil.connectWLST(server, username,
					password));
			isConnected = true;
		} catch (Exception e) {
			isConnected = false;
		}
		return isConnected;
	}

	public void listGlobalWorkManagers() {
		interpreter.exec(srvRuntime);
		interpreter.exec(WM);

		Integer getPendReq;
		PyObject result = (PyObject) interpreter.eval(lista + "\n");

		String[] sResult = result.toString().split("\n");
		String[] pResult;
		List<String> listResult = new ArrayList<String>();

		for (int i = 0; i < sResult.length; i++) {
			if (!sResult[i].equals("")) {
				pResult = sResult[i].split("\\s+");
				listResult.add(pResult[1]);

			} else
				System.out.println("No resource found");
		}
		for (String qResult : listResult) {

			compReq = (PyLong) interpreter.eval(mbeanGet + qResult
					+ completedRequests);
			pendReq = (PyInteger) interpreter.eval(mbeanGet + qResult
					+ pendingRequests);

			getComReq = (long) compReq.doubleValue();
			getPendReq = pendReq.getValue();
			System.out
					.println(qResult + " --- CompletedRequests: " + getComReq);
			System.out.println(qResult + " --- PendingRequests: " + getPendReq);
		}

	}

	public void listMinThreadsConstraint() {

		interpreter.exec("cd('/')");
		interpreter.exec(seflTUNING_MinThreads);

		PyObject result = (PyObject) interpreter.eval(lista + "\n");

		String[] sResult = result.toString().split("\n");
		String[] pResult;
		List<String> listResult = new ArrayList<String>();

		for (int i = 0; i < sResult.length; i++) {
			if (!sResult[i].equals("")) {
				pResult = sResult[i].split("\\s+");
				listResult.add(pResult[1]);

			} else
				System.out.println("No resource found");
		}
		for (String qResult : listResult) {

			compReq = (PyLong) interpreter.eval(mbeanGet + qResult
					+ completedRequests);
			pendReq = (PyInteger) interpreter.eval(mbeanGet + qResult
					+ pendingRequests);
			execReq = (PyInteger) interpreter.eval(mbeanGet + qResult
					+ pendingRequests);
			getComReq = (long) compReq.doubleValue();
			getPendReq = pendReq.getValue();
			getExecReq = execReq.getValue();
			System.out.println(qResult + " --- CompletedCount: " + getComReq);
			System.out.println(qResult + " --- PendingRequests: " + getPendReq);
			System.out.println(qResult + " --- ExecutingRequests: "
					+ getExecReq);
		}
	}

	// Get MaxThreads Constraint 
	public void listMaxThreadsConstraint() {

		interpreter.exec("cd('/')");
		interpreter.exec(seflTUNING_MaxThreads);

		PyObject result = (PyObject) interpreter.eval(lista + "\n");

		String[] sResult = result.toString().split("\n");
		String[] pResult;
		List<String> listResult = new ArrayList<String>();

		for (int i = 0; i < sResult.length; i++) {
			if (!sResult[i].equals("")) {
				pResult = sResult[i].split("\\s+");
				listResult.add(pResult[1]);

			} else
				System.out.println("Não possui recursos.");
		}
		for (String qResult : listResult) {

			difReq = (PyInteger) interpreter.eval(mbeanGet + qResult
					+ deferredRequests);
			execReq = (PyInteger) interpreter.eval(mbeanGet + qResult
					+ executingRequests);
			getDifReq = (long) difReq.getValue();
			getExecReq = execReq.getValue();
			System.out
					.println(qResult + " --- CompletedRequests: " + getComReq);
			System.out.println(qResult + " --- PendingRequests: " + getPendReq);
			System.out.println(qResult + " --- ExecutingRequests: "
					+ getExecReq);
		}
	}

	public void listRequestClass() {

		interpreter.exec("cd('/')");
		interpreter.exec(seflTUNING_RequestClass);

		PyObject result = (PyObject) interpreter.eval(lista + "\n");

		String[] sResult = result.toString().split("\n");
		String[] pResult;
		List<String> listResult = new ArrayList<String>();

		for (int i = 0; i < sResult.length; i++) {
			if (!sResult[i].equals("")) {
				pResult = sResult[i].split("\\s+");
				listResult.add(pResult[1]);

			} else
				System.out.println("No resource found");
		}
		for (String qResult : listResult) {

			compReq = (PyLong) interpreter.eval(mbeanGet + qResult
					+ completedCount);
			pendReq = (PyInteger) interpreter.eval(mbeanGet + qResult
					+ pendingRequestCount);
			totalThreads = (PyLong) interpreter.eval(mbeanGet + qResult
					+ totalThreadUse);
			getComReq = (long) compReq.doubleValue();
			getPendReq = pendReq.getValue();
			getTotalThreads = (long) totalThreads.doubleValue();
			System.out
					.println(qResult + " --- CompletedRequests: " + getComReq);
			System.out.println(qResult + " --- PendingRequests: " + getPendReq);
			System.out.println(qResult + " --- Total threads in use: "
					+ getTotalThreads);
		}
	}

}
