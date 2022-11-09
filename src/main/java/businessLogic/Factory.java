package businessLogic;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;

public class Factory {

	public BLFacade createBLFacade(boolean isLocal) {

		ConfigXML c=ConfigXML.getInstance();

		if(isLocal) {
			//In this option the DataAccess is created by FacadeImplementationWS
			//appFacadeInterface=new BLFacadeImplementation();

			//In this option, you can parameterize the DataAccess (e.g. a Mock DataAccess object)

			DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			return new BLFacadeImplementation(da);
		}
		else {
			String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
			 
			//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
			URL url=null;
			try {
				url = new URL(serviceName);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	 
	        //1st argument refers to wsdl document above
			//2nd argument is service name, refer to wsdl document above
//	        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
	        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
	 
	        Service service = Service.create(url, qname);

	        return service.getPort(BLFacade.class);
		}
	}
}
