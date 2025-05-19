package com.zed.exam;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;


    @GetMapping("/home")
    public String home(Model model) {
        return "layout/main";
    }

    /**
     *
     * @param search used to search for the variable that is wanted by the user
     * @param model used to add attributes
     * @return it returns te main page of the program
     */
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, HttpSession session, Model model) {
//        List<CoffeeExam> coffeeList = coffeeService.searchCoffee(search);
//        model.addAttribute("coffees", coffeeList);
        AppUser user = (AppUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        model.addAttribute("coffee", coffeeService.searchCoffee(search));
        model.addAttribute("activeMenu", "home");

        return "index";
    }

    @GetMapping("/catalog")
    public String catalog(Model model, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        model.addAttribute("coffee", coffeeService.getCoffeeExamList());
        return "catalog";
    }

    /**
     *
     * @param id - (int) id of the coffee
     * @return - deletes the coffee that is listed
     */
    @GetMapping("/delete")
    public String deleteCoffee(@RequestParam int id, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        coffeeService.deleteCoffeeExam(id);
        return "redirect:/";
    }

    /**
     *
     * @return - goes to the new html for the adding of new coffee
     */
    @GetMapping("/add")
    public String add(Model model, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
        model.addAttribute("types", new String[]{"Frappe", "Espresso", "Americano", "Latte", "Cappuccino", "Mocha", "Flat White", "Iced Coffee"});
        model.addAttribute("sizes", new String[]{"Small", "Medium", "Large"});
        model.addAttribute("roastLevels", new String[]{"Light", "Medium", "Dark"});
        model.addAttribute("brewMethods", new String[]{"Drip", "French Press", "Espresso", "Filter"});

        CoffeeExam coffeeExam = new CoffeeExam();
        model.addAttribute("coffeeExam", coffeeExam);
        model.addAttribute("activeMenu", "new");

        return "new";
    }

    /**
     *
     * @param coffeeExam shortcut
     * @param bindingResult used to catch errors
     * @return new.html if value has errors & home if all is functional
     */
    @PostMapping("/save")
    public String save(@ModelAttribute("coffeeExam") @Valid CoffeeExam coffeeExam, BindingResult bindingResult, @RequestParam(value = "imageFile") MultipartFile coffeePicture, Model model, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("types", new String[]{"Frappe", "Espresso", "Americano", "Latte", "Cappuccino", "Mocha", "Flat White", "Iced Coffee"});
            model.addAttribute("sizes", new String[]{"Small", "Medium", "Large"});
            model.addAttribute("roastLevels", new String[]{"Light", "Medium", "Dark"});
            model.addAttribute("brewMethods", new String[]{"Drip", "French Press", "Espresso", "Filter"});
            return "new";
        }

        coffeeExam.setId(coffeeService.getId() + 1);

        // Handle image upload
        if (!coffeePicture.isEmpty()) {

            String contentType = coffeePicture.getContentType();
            if (!contentType.startsWith("image")) {
                System.out.println("File is not an image: " + coffeePicture.getOriginalFilename());
                bindingResult.rejectValue("coffeePicture", "error.coffeePicture", "The uploaded file is not an image.");
                return "new";
            }

            String path = "data/coffee_pictures/";
            File uploadFolder = new File(path);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            String fileName = UUID.randomUUID() + coffeePicture.getOriginalFilename().substring(coffeePicture.getOriginalFilename().lastIndexOf("."));
            try {
                coffeePicture.transferTo(new File(uploadFolder.getAbsolutePath() + File.separator + fileName));
                coffeeExam.setCoffeePicture(fileName);
                System.out.println(coffeeExam.getCoffeePicture());
            } catch (IOException e) {
                System.out.println("File upload error: " + e.getMessage());
            }
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
    public String edit(@RequestParam int id, Model model, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }
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
    public String update(@ModelAttribute("coffeeExam") @Valid CoffeeExam coffeeExam, BindingResult bindingResult, Model model, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("coffeeExam", coffeeExam);
            model.addAttribute("types", new String[]{"Frappe", "Espresso", "Americano", "Latte", "Cappuccino", "Mocha", "Flat White", "Iced Coffee"});
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

    @GetMapping("/coffee/{id}")
    public String view(@PathVariable int id, Model model, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        }

        CoffeeExam c = coffeeService.getCoffee(id);
        model.addAttribute("coffeeExam", c);
        return "coffee";
    }
}
