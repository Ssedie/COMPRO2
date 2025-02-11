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


    public double calculatePrize(String size){
        double defaultPrice = 0;
        switch(size.toLowerCase()){
            case "small":
                defaultPrice = 1.01;
                break;
            case "medium":
                defaultPrice = 1.25;
                break;
            case "large":
                defaultPrice = 1.5;
                break;
            default:
                defaultPrice = 1.0;
        }

        return this.price * defaultPrice;
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

    public String getBrewMethod() {
        return brewMethod;
    }

    void setBrewMethod(String brewMethod) {
        this.brewMethod = brewMethod;
    }

    public boolean checkStock() {
        return stock > 0;
    }

    void updateStock(int quantity){
        this.stock = quantity;
    }

    public String explain() {
        StringBuilder flavorDescription = new StringBuilder();
        if (flavorNoteCount == 0) {
            flavorDescription.append("No flavor notes");
        } else {
            for (int i = 0; i < flavorNoteCount; i++) {
                flavorDescription.append(flavorNotes[i]);
                if (i < flavorNoteCount - 1) {
                    flavorDescription.append(", ");
                }
            }
        }
        return String.format("A %s roast coffee from %s with flavor notes of %s brewed using %s.",
                roastLevel, origin, flavorDescription.toString(), brewMethod);
    }

    void changeRoastLevel(String newRoastLevel) {
        this.roastLevel = newRoastLevel;
    }

    public double getPrice() {
        return price;
    }

    void setPrice(double price) {
        this.price = price;
    }

    public String getRoastLevel() {
        return roastLevel;
    }

    void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    public String getOrigin() {
        return origin;
    }

    void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isDecaf() {
        return isDecaf;
    }

    void setDecaf(boolean isDecaf){
        this.isDecaf = isDecaf;
    }

    public int getStock() {
        return stock;
    }

    void setStock(int stock) {
        this.stock = stock;
    }


    void discount(double percent){
        this.price -= this.price * (percent/100);
    }

    public String getName(){
        return name;
    }

    void setName(String name){
        this.name = name;
    }

    public String getType(){
        return type;
    }

    void setType(String type){
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    void setSize(String size) {
        this.size = size;
    }

    public static void main(String[] args) {
        Coffee o1 = new Coffee("Bulgarian", "Espresso", "small", 75.50, "light", "Philippine Islands", false, 3, "Trickle");

        o1.addFlavor("Strawberry");
        o1.calculatePrize("small");
        o1.discount(10);
        o1.updateStock(5);
        System.out.println(o1.getName() + " " + o1.getType() + " " + o1.getSize());
        System.out.println(o1.explain());
        System.out.println("The total price for the coffee is: € " + o1.getPrice());

    }


}
