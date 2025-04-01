package com.zed.exam;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    private ArrayList<CoffeeExam> coffeeExamArrayList;
    private final String FILE_NAME = "database.csv";

    public CoffeeService() {
        coffeeExamArrayList = new ArrayList<>();
        readFromDisk();
    }

    public ArrayList<CoffeeExam> getCoffeeExamArrayList() {
        return coffeeExamArrayList;
    }

    public void deleteCoffeeExam(int id) {
        coffeeExamArrayList.removeIf(coffeeExam -> coffeeExam.getId() == id);
        writeToDisk();
    }

    public List<CoffeeExam> searchCoffee(String keyword){
        if(keyword.trim().isEmpty()){
            return new ArrayList<>(coffeeExamArrayList);
        }

        return coffeeExamArrayList.stream().filter(s ->
                s.getName() != null && s.getName().toLowerCase().contains(keyword.toLowerCase())
                        || s.getType() != null && s.getType().toLowerCase().contains(keyword.toLowerCase())
                        || s.getSize() != null && s.getSize().toLowerCase().contains(keyword.toLowerCase())
                        || s.getBrewMethod() != null && s.getBrewMethod().toLowerCase().contains(keyword.toLowerCase())
                        || s.getFlavorNotes() != null && s.getFlavorNotes().contains(keyword.toLowerCase())
                        || s.getRoastLevel() != null && s.getRoastLevel().toLowerCase().contains(keyword.toLowerCase())
                        || s.getOrigin() != null && s.getOrigin().toLowerCase().contains(keyword.toLowerCase())
        ).collect(Collectors.toList());
    }

    public CoffeeExam getCoffee(int id){
        for(CoffeeExam s: coffeeExamArrayList){
            if(s.getId() == id)
                return s;
        }
        return null;
    }

    public void updateCoffee(int id, CoffeeExam update){
        for(int i = 0; i < coffeeExamArrayList.size(); i++){
            if(coffeeExamArrayList.get(i).getId() == id){
                coffeeExamArrayList.set(i, update);
                writeToDisk();
                break;
            }
        }
    }

    public void addCoffee(CoffeeExam coffeeExam){
        coffeeExamArrayList.add(coffeeExam);
        writeToDisk();
    }

    public int getId(){
        if(coffeeExamArrayList.isEmpty()){
            return 0;
        }
        return coffeeExamArrayList.get(coffeeExamArrayList.size() - 1).getId();
    }

    public void writeToDisk(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            //write the content of the arraylist into csv
            System.out.println("Writing to file");
            for(CoffeeExam s : coffeeExamArrayList){
                String line = s.getId() + ","
                        + s.getName() + ","
                        + s.getType() + ","
                        + s.getSize() + ","
                        + s.getPrice() + ","
                        + s.getRoastLevel() + ","
                        + s.getOrigin() + ","
                        + s.isDecaf() + ","
                        + s.getStock() + ","
                        +  s.getBrewMethod() + ","
                        + String.join(";",s.getFlavorNotes() != null ? s.getFlavorNotes() : new ArrayList<>());
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Done writing to file");
        }catch(IOException e){
            System.out.println("Woah! Error: " + e.getMessage());
        }
    }

    /**
     * This read the CSV file and loads it to the students ArrayList
     */
    public void readFromDisk(){
        File file = new File(FILE_NAME);
        if(!file.exists()){
            System.out.println("file not found");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                if (data.length < 11) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                CoffeeExam c = new CoffeeExam();
                c.setId(Integer.parseInt(data[0]));
                c.setName(data[1]);
                c.setType(data[2]);
                c.setSize(data[3]);
                c.setPrice(Double.parseDouble(data[4]));
                c.setRoastLevel(data[5]);
                c.setOrigin(data[6]);
                c.setDecaf(Boolean.parseBoolean(data[7]));
                c.setStock(Integer.parseInt(data[8]));
                c.setBrewMethod(data[9]);
                c.setFlavorNotes(data[10].isEmpty() ? new ArrayList<>() : new ArrayList<>(Arrays.asList(data[10].split(","))));
                //add coffee to the list
                coffeeExamArrayList.add(c);
            }
            System.out.println("Done reading from file");
        }catch(IOException e){
            System.out.println("Wow! Error: " + e.getMessage());
        }
    }
}
