package om.foss.demo.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;
 
    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("tasks", tasks);
        return "home";
    }
    
    
 // /hello?name=foss
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") 
			String name, Model model) {

        model.addAttribute("appName", name);
        model.addAttribute("tasks", tasks);
        return "home";
    }
}