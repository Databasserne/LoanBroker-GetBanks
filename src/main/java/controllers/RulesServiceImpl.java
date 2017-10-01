package controllers;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

@WebServiceClient(name = "RulesServiceService",
    targetNamespace = "http://webservices.loanbroker.databasserne.com/",
    wsdlLocation = "http://localhost:9999/rules/banks?wsdl")
public class RulesServiceImpl extends Service {

    private final static URL HELLOWORLDIMPLSERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9999/rules/banks?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HELLOWORLDIMPLSERVICE_WSDL_LOCATION = url;
    }

    public RulesServiceImpl(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RulesServiceImpl() {
        super(HELLOWORLDIMPLSERVICE_WSDL_LOCATION, new QName("http://webservices.loanbroker.databasserne.com/", "RulesServiceService"));
    }

    @WebEndpoint(name = "RulesServiceImplPort")
    public IRulesService getRulesServiceImplPort() {
        return (IRulesService) super.getPort(
                new QName("http://webservices.loanbroker.databasserne.com/", "RulesServicePort"),
                IRulesService.class);
    }

    @WebEndpoint(name = "RulesServiceImplPort")
    public IRulesService getRulesServiceImplPort(WebServiceFeature... features) {
        return (IRulesService) super.getPort(
                new QName("http://webservices.loanbroker.databasserne.com/", "RulesServicePort"),
                IRulesService.class,
                features);
    }

}
