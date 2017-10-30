package spring.boot;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Hello world!
 *
 */
@Controller
@RequestMapping("/hello")
public class App 
{
    @RequestMapping("/")
    @ResponseBody
    public String hello(){
    	 return "spring-boot";
    }
    @RequestMapping("/info")
    @ResponseBody
    public Map<String,String> getInfo(@RequestParam String name){
    	Map<String,String> maps=Maps.newHashMap();
    	maps.put("name", name);
    	return maps;
    }
    @RequestMapping("/info/{name}")
    @ResponseBody
    public Map<String,String> getInfo2(@PathVariable("name") String name){
    	Map<String,String> maps=Maps.newHashMap();
    	maps.put("name", name);
    	return maps;
    }
    @RequestMapping("/list")
    @ResponseBody
    public List<Map<String,String>> getList(){
    	 List<Map<String,String>> list=Lists.newArrayList();
    	 Map<String,String> map=Maps.newHashMap();
    	 for(int i=1;i<5;i++){
    		 map.put("name", "ttt"+i);
    		 list.add(map);
    	 }
    	 return list;
    }
    @RequestMapping("/test")
    public String getName(Model model){
    	 model.addAttribute("name", "susu");
    	 return "index";
    }
}
