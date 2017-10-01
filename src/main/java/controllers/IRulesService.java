package controllers;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "RulesService", targetNamespace = "http://webservices.loanbroker.databasserne.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IRulesService {

    @WebMethod
    @WebResult(partName = "return")
    public String getBanks(
            @WebParam(name = "creditScore", partName = "creditScore") int creditScore,
            @WebParam(name = "amount", partName = "amount") double amount,
            @WebParam(name = "months", partName = "months") int months);
}
