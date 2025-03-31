package com.zed.exam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteCoffeeExam(CoffeeExam coffeeExam) {
        coffeeExamArrayList.removeIf(coffeeExam1 -> coffeeExam1.getId() == coffeeExam.getId());
        writeToDisk();
    }

    public List<CoffeeExam> searchCoffee(String keyword){
        if(keyword.trim().isEmpty()){
            return new ArrayList<>();
        }

        return coffeeExamArrayList.stream().filter(s ->
                s.getName().toLowerCase().contains(keyword.toLowerCase())
                        || s.getType().toLowerCase().contains(keyword.toLowerCase())
                        || s.getSize().toLowerCase().contains(keyword.toLowerCase())
                        || s.getBrewMethod().toLowerCase().contains(keyword.toLowerCase())
                        || s.getFlavorNotes().contains(keyword.toLowerCase())
                        || s.getRoastLevel().toLowerCase().contains(keyword.toLowerCase())
                        || s.getOrigin().toLowerCase().contains(keyword.toLowerCase())
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

    public void addStudent(CoffeeExam coffeeExam){
        coffeeExamArrayList.add(coffeeExam);
        writeToDisk();
    }

    public int getId(){
        if(coffeeExamArrayList.isEmpty()){
            return 0;
        }
        return coffeeExamArrayList.get(coffeeExamArrayList.size()-1).getId();
    }

    public void writeToDisk(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            //write the content of the arraylist into csv
            for(CoffeeExam s : coffeeExamArrayList){
                bw.write(s.getId() + ","
                        + s.getName() + ","
                        + s.getType() + ","
                        + s.getSize() + ","
                        + s.getPrice() + ","
                        + s.getRoastLevel() + ","
                        + s.getOrigin() + ","
                        + s.isDecaf() + ","
                        + s.getStock() + ","
                        + s.getBrewMethod() + ","
                        + s.getFlavorNotes()
                );
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println("Uh-oh! Error: " + e.getMessage());
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

                CoffeeExam s = new CoffeeExam();
                s.setId(Integer.parseInt(data[0]));
                s.setName(data[1]);
                s.setType(data[2]);
                s.setSize(data[3]);
                s.setPrice(Integer.parseInt(data[4]));
                s.setRoastLevel(data[5]);
                s.setOrigin(data[6]);
                s.setDecaf(Boolean.parseBoolean(data[7]));
                s.setStock(Integer.parseInt(data[8]));
                s.setBrewMethod(data[9]);
                //add coffee to the list
                coffeeExamArrayList.add(s);
            }
        }catch(IOException e){
            System.out.println("Uh-oh! Error: " + e.getMessage());
        }
    }
}
