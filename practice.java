public class practice {

    // Properties (Attributes)
    private String name;
    private String type;
    private String size;
    private double price;
    private String roastLevel;
    private String origin;
    private boolean isDecaf;
    private int stock;
    private String[] flavorNotes;
    private String brewMethod;
    private int flavorNoteCount; // to track the number of flavor notes

    // Constructor
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
        this.flavorNotes = new String[10]; // Limit of 10 flavor notes
        this.flavorNoteCount = 0;
    }

    // Method to calculate the price based on the size of the coffee
    public double calculatePrice(String size) {
        double priceModifier = 0;
        switch (size) {
            case "Small":
                priceModifier = 1.0;
                break;
            case "Medium":
                priceModifier = 1.2;
                break;
            case "Large":
                priceModifier = 1.5;
                break;
            default:
                priceModifier = 1.0;
        }
        return this.price * priceModifier;
    }

    // Method to check if the coffee is in stock
    public boolean checkStock() {
        return stock > 0;
    }

    // Method to add a flavor note to the coffee
    public void addFlavor(String note) {
        if (flavorNoteCount < flavorNotes.length) {
            flavorNotes[flavorNoteCount] = note;
            flavorNoteCount++;
        } else {
            System.out.println("Cannot add more flavor notes. Limit reached.");
        }
    }

    // Method to update the stock quantity of the coffee
    public void updateStock(int quantity) {
        this.stock = quantity;
    }

    // Method to return a description of the coffee
    public String describe() {
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

    // Method to update whether the coffee is decaffeinated
    public void setDecaf(boolean isDecaf) {
        this.isDecaf = isDecaf;
    }

    // Method to change the roast level of the coffee
    public void changeRoastLevel(String newRoastLevel) {
        this.roastLevel = newRoastLevel;
    }

    // Method to apply a discount to the price of the coffee
    public void discount(double percentage) {
        this.price -= this.price * (percentage / 100);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isDecaf() {
        return isDecaf;
    }

    public void setDecaf(boolean decaf) {
        isDecaf = decaf;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String[] getFlavorNotes() {
        return flavorNotes;
    }

    public void setFlavorNotes(String[] flavorNotes) {
        this.flavorNotes = flavorNotes;
    }

    public String getBrewMethod() {
        return brewMethod;
    }

    public void setBrewMethod(String brewMethod) {
        this.brewMethod = brewMethod;
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        Coffee coffee = new Coffee("Espresso", "Arabica", "Medium", 5.0, "Dark", "Colombia", false, 10, "Espresso");

        // Demonstrate adding flavor notes
        coffee.addFlavor("Chocolate");
        coffee.addFlavor("Nutty");

        // Check stock
        System.out.println("Is the coffee in stock? " + coffee.checkStock());

        // Calculate price based on size
        System.out.println("Price for Medium size: $" + coffee.calculatePrice("Medium"));

        // Describe the coffee
        System.out.println(coffee.describe());

        // Change roast level
        coffee.changeRoastLevel("Medium");
        System.out.println("New roast level: " + coffee.getRoastLevel());

        // Apply a discount
        coffee.discount(10);
        System.out.println("Price after discount: $" + coffee.getPrice());

        // Update stock
        coffee.updateStock(5);
        System.out.println("Updated stock: " + coffee.getStock());
    }
}

