package controllers;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClientController {

    private RulesServiceImpl rulesService;
    private IRulesService iRules;

    public ClientController() {
        rulesService = new RulesServiceImpl();
        iRules = rulesService.getRulesServiceImplPort();
    }

    public String requestBanks(int creditScore, double amount, int months) {
        System.out.println(iRules.getBanks(creditScore, amount, months));
        return iRules.getBanks(creditScore, amount, months);
    }
}
