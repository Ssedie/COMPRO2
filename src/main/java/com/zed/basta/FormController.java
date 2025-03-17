package com.zed.basta;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("pip", new People());
        return "/form";
    }

    @PostMapping("/submit")
    @ResponseBody
    public String processForm(@RequestParam Map<String, String> formData) {
        System.out.println("Form Data Received:");
        formData.forEach((k, v) -> System.out.println(k + ": " + v));
        return "Form submitted successfully! Check terminal for output.";
    }

    class People {
        String name;
        String password;
        String email;
        int age;
        String gender;
        String[] interests;
        String country;
        String message;
        String dob;


        public People() {
        }

        public People(String name, String password, String email, int age, String gender, String[] interests, String country, String message, String dob) {
            this.name = name;
            this.password = password;
            this.email = email;
            this.age = age;
            this.gender = gender;
            this.interests = interests;
            this.country = country;
            this.message = message;
            this.dob = dob;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getGender() {
            return gender;
        }
        public void setGender(String gender) {
            this.gender = gender;
        }
        public String[] getInterests() {
            return interests;
        }
        public void setInterests(String[] interests) {
            this.interests = interests;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public String getDob() {
            return dob;
        }
        public void setDob(String dob) {
            this.dob = dob;
        }
    }
}
