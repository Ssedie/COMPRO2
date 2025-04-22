package com.zed.exam;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;


    /**
     *
     * @param search used to search for the variable that is wanted by the user
     * @param model used to add attributes
     * @return it returns te main page of the program
     */
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, Model model) {
//        List<CoffeeExam> coffeeList = coffeeService.searchCoffee(search);
//        model.addAttribute("coffees", coffeeList);
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
        coffeeService.deleteCoffeeExam(id);
        return "redirect:/";
    }

    /**
     *
     * @return - goes to the new html for the adding of new coffee
     */
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("types", new String[]{"Frappe", "Espresso", "Americano", "Latte", "Cappuccino", "Mocha", "Flat White", "Iced Coffee"});
        model.addAttribute("sizes", new String[]{"Small", "Medium", "Large"});
        model.addAttribute("roastLevels", new String[]{"Light", "Medium", "Dark"});
        model.addAttribute("brewMethods", new String[]{"Drip", "French Press", "Espresso", "Filter"});

        CoffeeExam coffeeExam = new CoffeeExam();
        model.addAttribute("coffeeExam", coffeeExam);
        return "new";
    }

    /**
     *
     * @param coffeeExam shortcut
     * @param bindingResult used to catch errors
     * @return new.html if value has errors & home if all is functional
     */
    @PostMapping("/save")
    public String save(@ModelAttribute("coffeeExam") @Valid CoffeeExam coffeeExam, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", new String[]{"Frappe", "Espresso", "Americano", "Latte", "Cappuccino", "Mocha", "Flat White", "Iced Coffee"});
            model.addAttribute("sizes", new String[]{"Small", "Medium", "Large"});
            model.addAttribute("roastLevels", new String[]{"Light", "Medium", "Dark"});
            model.addAttribute("brewMethods", new String[]{"Drip", "French Press", "Espresso", "Filter"});
            return "new";
        }

        coffeeService.addCoffee(coffeeExam);
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
        CoffeeExam c = coffeeService.getCoffee(id);
        if(c != null){
            model.addAttribute("coffeeExam", c);
            model.addAttribute("types", new String[]{"Frappe", "Espresso", "Americano", "Latte", "Cappuccino", "Mocha", "Flat White", "Iced Coffee"});
            model.addAttribute("sizes", new String[]{"Small", "Medium", "Large"});
            model.addAttribute("roastLevels", new String[]{"Light", "Medium", "Dark"});
            model.addAttribute("brewMethods", new String[]{"Drip", "French Press", "Espresso", "Filter"});

            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("coffeeExam") @Valid CoffeeExam coffeeExam, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("coffee", coffeeExam);
            model.addAttribute("types", new String[]{"Frappe", "Espresso", "Americano"});
            model.addAttribute("sizes", new String[]{"Small", "Medium", "Large"});
            model.addAttribute("roastLevels", new String[]{"Light", "Medium", "Dark"});
            model.addAttribute("brewMethods", new String[]{"Drip", "French Press", "Espresso", "Filter"});
            return "edit";
        }

        CoffeeExam c = coffeeService.getCoffee(coffeeExam.getId());
        if(c != null){
            coffeeService.updateCoffee(coffeeExam.getId(), coffeeExam);
        }
        return "redirect:/";
    }
}
