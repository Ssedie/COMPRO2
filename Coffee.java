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
}
