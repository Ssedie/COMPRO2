package com.zed.exam;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    CoffeeService coffeeService;

    /**
     *
     */
    public Controller(){
        coffeeService = new CoffeeService();
    }


    /**
     *
     * @param search used to search for the variable that is wanted by the user
     * @param model used to add attributes
     * @return
     */
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, Model model) {
        model.addAttribute("coffee", coffeeService.searchCoffee(search));

        return "index";
    }

    /**
     *
     * @param id - (int) id of the coffee
     * @return - deletes the coffee that is listed
     */
    @GetMapping("/delete")
    public String deleteCoffee(@RequestParam int id){
        coffeeList.removeIf(coffee -> coffee.getId() == id);
        return "redirect:/";
    }

    /**
     *
     * @return - goes to the new html for the adding of new coffee
     */
    @GetMapping("/add")
    public String add(){
        return "new";
    }

    /**
     *
     * @param name (String) name of the coffee
     * @param type (String) type of the coffee
     * @param size (String) size of the coffee
     * @param price (int) price for the coffee
     * @param roastLevel (String) roast level of the coffee
     * @param origin (String) origin of the coffee
     * @param isDecaf (boolean) is it decaf or not?
     * @param stock (int) stock for the coffee
     * @param flavorNotes (String) flavor notes for the coffee
     * @param brewMethod (String) brewing method for the coffee
     * @return returns to the main page where the coffee is listed
     */
    @PostMapping("/save")
    public String save(@RequestParam String name,
                       @RequestParam String type,
                       @RequestParam String size,
                       @RequestParam double price,
                       @RequestParam String roastLevel,
                       @RequestParam String origin,
                       @RequestParam boolean isDecaf,
                       @RequestParam int stock,
                       @RequestParam List<String> flavorNotes,
                       @RequestParam String brewMethod){
        CoffeeExam c = new CoffeeExam();
        c.setId(coffeeService.size() + 1);
        c.setName(name);
        c.setType(type);
        c.setSize(size);
        c.setPrice(price);
        c.setRoastLevel(roastLevel);
        c.setOrigin(origin);
        c.setDecaf(isDecaf);
        c.setStock(stock);
        c.setFlavorNotes(flavorNotes);
        c.setBrewMethod(brewMethod);
        coffeeService.add(c);
        return "redirect:/";
    }

    /**
     *
     * @param id - (int) id of the coffee
     * @param model - used to display the properties of the coffee
     * @return - goes to the edit.html and allows the user to edit the desired property of the coffee
     */
    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        for (CoffeeExam coffee : coffeeList) {
            if (coffee.getId() == id) {
                model.addAttribute("coffee", coffee);
                return "edit";
            }
        }
        return "redirect:/";
    }

    /**
     *
     * @param id - (id) id of the coffee
     * @param name - (String) name of the coffee
     * @param type - (String) type of the coffee
     * @param size - (String) size of the coffee
     * @param price - (int) price for the coffee
     * @param roastLevel - (String) roast level of the coffee
     * @param origin - (String) origin of the coffee
     * @param isDecaf - (boolean) is it decaf or not?
     * @param stock - (int) stock for the coffee
     * @param flavorNotes - (String) flavor notes for the coffee
     * @param brewMethod - (String) brewing method for the coffee
     * @return - allows the page to recognize updates made in the edit.html and shows it in the main page after updating
     */
    @PostMapping("/update")
    public String update(@RequestParam int id,
                         @RequestParam String name,
                         @RequestParam String type,
                         @RequestParam String size,
                         @RequestParam double price,
                         @RequestParam String roastLevel,
                         @RequestParam String origin,
                         @RequestParam(required = false) boolean isDecaf,
                         @RequestParam int stock,
                         @RequestParam String flavorNotes,
                         @RequestParam String brewMethod) {

        for (CoffeeExam coffee : coffeeList) {
            if (coffee.getId() == id) {
                coffee.setName(name);
                coffee.setType(type);
                coffee.setSize(size);
                coffee.setPrice(price);
                coffee.setRoastLevel(roastLevel);
                coffee.setOrigin(origin);
                coffee.setDecaf(isDecaf);
                coffee.setStock(stock);
                coffee.setFlavorNotes(Arrays.asList(flavorNotes.split(", ")));
                coffee.setBrewMethod(brewMethod);
                break;
            }
        }
        return "redirect:/";
    }
}
