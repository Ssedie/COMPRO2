import java.util.Scanner;

public class Coffee {

    String name;
    String type;
    String size;
    double price;
    String roastLevel;
    String origin;
    String[] flavorNotes;
    int flavorNoteCount;
    String brewMethod;
    boolean isDecaf;
    int stock;


    public Coffee(String name, String type, String size, double price, String roastLevel, String origin,
                boolean isDecaf, int stock, String brewMethod) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.roastLevel = roastLevel;
        this.origin = origin;
        this.isDecaf = isDecaf;
        this.stock = stock;
        this.brewMethod = brewMethod;
        this.flavorNotes = new String[5];
        this.flavorNoteCount = 0;
    }


    double calculatePrize(String size){
        double adjustPrice = 0;
        switch(size){
            case "Small":
                adjustPrice = 1.0;
                break;
            case "Medium":
                adjustPrice = 1.3;
                break;
            case "Large":
                adjustPrice = 1.5;
                break;
            default:
                adjustPrice = 1.0;
        }

        return this.price * adjustPrice;
    }

    void addFlavor(String flavor){
        if (flavorNoteCount < flavorNotes.length){
            flavorNotes[flavorNoteCount] = flavor;
            flavorNoteCount++;
        }
    }

    void setFlavorNotes(String[] flavorNotes) {
        this.flavorNotes = flavorNotes;
    }

    String getBrewMethod() {
        return brewMethod;
    }

    void setBrewMethod(String brewMethod) {
        this.brewMethod = brewMethod;
    }

    boolean checkStock() {
        return stock > 0;
    }

    void updateStock(int quantity){
        this.stock = quantity;
    }

    void changeRoastLevel(String newRoastLevel) {
        this.roastLevel = newRoastLevel;
    }

    double getPrice() {
        return price;
    }

    void setPrice(double price) {
        this.price = price;
    }

    String getRoastLevel() {
        return roastLevel;
    }

    void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    String getOrigin() {
        return origin;
    }

    void setOrigin(String origin) {
        this.origin = origin;
    }

    boolean isDecaf() {
        return isDecaf;
    }

    void setDecaf(boolean isDecaf){
        this.isDecaf = isDecaf;
    }

    int getStock() {
        return stock;
    }

    void setStock(int stock) {
        this.stock = stock;
    }

    void updateStock(int quantity){
        this.stock = quantity;
    }

    void discount(double percent){
        this.price -= this.price * (percent/100);
    }

    String getName(){
        return name;
    }

    void setName(String name){
        this.name = name;
    }

    String getType(){
        return type;
    }

    void setType(String type){
        this.type = type;
    }

    String getSize() {
        return size;
    }

    void setSize(String size) {
        this.size = size;
    }


}
