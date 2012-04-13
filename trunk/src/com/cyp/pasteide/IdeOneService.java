package com.cyp.pasteide;

import java.rmi.RemoteException;
import java.util.HashMap;

import com.ideone.api._1.service.Ideone_Service_v1Port;
import com.ideone.api._1.service.Ideone_Service_v1Service;
import com.ideone.api._1.service.Ideone_Service_v1ServiceLocator;


public class IdeOneService {
	private String user;
	private String pass;
	Ideone_Service_v1Service s=null;
	Ideone_Service_v1Port port=null;
	
	public IdeOneService(String user,String passwd){
		this.user = user;
		this.pass = passwd;
		s = new Ideone_Service_v1ServiceLocator();
		try {
			port = s.getIdeone_Service_v1Port();
		}catch(Exception e){
			
		}
	}
    public void setUser(String user){
    	this.user = user;
    }
    public void setPass(String pass){
    	this.pass = pass;
    }
    private void log(String str){
        System.out.println(str);
    }
    public HashMap getSubmissionDetails(String link,boolean withSource,boolean withInput, boolean withOutput,boolean withStderr,boolean withCmpinfo) throws RemoteException{
    	Object ret[] = port.getSubmissionDetails(user, pass, link, withSource, withInput, withOutput, withStderr, withCmpinfo);
    	return (HashMap) ret[0];
    }
    public HashMap getSubmissionStatus(String link) throws RemoteException{
    	Object ret[]= port.getSubmissionStatus(user, pass, link);
    	return (HashMap) ret[0];
    }
    public HashMap createSubmission(String sourceCode,int language, String input, boolean run,boolean _private) throws RemoteException{
    	Object ret[]  = port.createSubmission(user, pass, sourceCode, language, input, run, _private);
    	return (HashMap)ret[0];
    }
    public HashMap getLanguages() throws RemoteException{
    	Object ret[] = port.getLanguages(user, pass);
    	//log(ret.length+"");
    	HashMap hm = (HashMap) ret[0];
    	if(hm.get("languages")!=null){
    		return (HashMap) hm.get("languages");
    	}
    	return null;
    }
}
