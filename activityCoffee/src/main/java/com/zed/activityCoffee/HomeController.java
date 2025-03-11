package com.zed.activityCoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Controller
public class HomeController {
	private List<Coffee> coffeeList = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(HomeController.class, args);
	}

	public HomeController(){
		coffeeList.add(new Coffee(1, "Espresso", "Arabica", "Small", 3.50, "Dark", "Ethiopia", false, 10, Arrays.asList("Chocolate", "Nutty"), "Espresso"));
		coffeeList.add(new Coffee(2, "Latte", "Arabica", "Medium", 4.50, "Medium", "Brazil", false, 8, Arrays.asList("Creamy", "Sweet"), "Drip"));
		coffeeList.add(new Coffee(3, "Cappuccino", "Robusta", "Large", 5.00, "Medium", "Colombia", false, 12, Arrays.asList("Fruity", "Bold"), "French Press"));
		coffeeList.add(new Coffee(4, "Mocha", "Arabica", "Medium", 4.75, "Dark", "Guatemala", false, 6, Arrays.asList("Chocolate", "Smooth"), "Espresso"));
		coffeeList.add(new Coffee(5, "Americano", "Robusta", "Large", 3.25, "Light", "Kenya", false, 15, Arrays.asList("Citrus", "Balanced"), "Drip"));
		coffeeList.add(new Coffee(6,"Caramel", "Frappe", "Large", 4.25, "Light", "Pilipins", false, 10, Arrays.asList("Strawberry", "Coarse"), "Trickle"));
	}

	/**
	 *
	 * @param model - used in displaying the list of coffees
	 * @return - shows the main page where the coffee is listed
	 */
	@GetMapping("/")
	public String getCoffees(Model model){
		model.addAttribute("coffees", coffeeList);
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
	 * @return - returns to the main page where the coffee is listed
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
		Coffee c = new Coffee();
		c.setId(coffeeList.size() + 1);
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
		coffeeList.add(c);
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
		for (Coffee coffee : coffeeList) {
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

		for (Coffee coffee : coffeeList) {
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
