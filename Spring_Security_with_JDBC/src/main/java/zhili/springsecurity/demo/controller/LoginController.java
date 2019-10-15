/**
 * @author zhilic created on 9/26/19
 */

package zhili.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showHome() {
        return "home";   // look for "/WEB-INF/view/home.jsp" based on config file
    }
    
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
    	return "login";
    }
    
    @GetMapping("/leaders")
    public String showLeaders() {
    	return "leaders";
    }
    
    @GetMapping("/systems")
    public String showSystems() {
    	return "systems";
    }
    
    @GetMapping("/access-denied")
    public String showAcessDenied() {
    	return "access-denied";
    }

}
