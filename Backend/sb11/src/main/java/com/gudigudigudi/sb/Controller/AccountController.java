package com.gudigudigudi.sb.Controller;

import com.gudigudigudi.sb.Model.Account;
import com.gudigudigudi.sb.Service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by gu on 4/4/17.
 */
@Controller
@RequestMapping("account")
public class AccountController {

    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        modelMap.put("account",new Account());
        return "account/index";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute("account") Account account,
                        HttpSession session,
                        ModelMap modelMap) {
        if (accountService.findByUsernameAndPassword(
                account.getUserName(),
                account.getPassword()) != null) {
            session.setAttribute("username",account.getUserName());
            return "account/welcome";
        } else {
            modelMap.put("error","Account's Invalid");
            return "account/index";
        }
    }
}
