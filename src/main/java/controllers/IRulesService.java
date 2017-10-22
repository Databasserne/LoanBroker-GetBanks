package controllers;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "IRules", targetNamespace = "http://webservices.loanbroker.databasserne.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IRulesService {

    @WebMethod
    @WebResult(partName = "return")
    public String getBanks(
            @WebParam(name = "arg0") int creditScore,
            @WebParam(name = "arg1") double amount,
            @WebParam(name = "arg2") int months);
}
