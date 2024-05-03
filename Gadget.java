public class Gadget {
    private String model;
    private double price; // Price is in pounds and can have decimal values.
    private int weight; // Weight is in grams.
    private String size; // Example format: "71mm x 137mm x 9mm"

    // Constructor to initialize the attributes.
    public Gadget(String model, double price, int weight, String size) {
        this.model = model;
        this.price = price;
        this.weight = weight;
        this.size = size;
    }

    // Accessor methods to retrieve the attributes.
    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String getSize() {
        return size;
    }

    // Display method to output the gadget details.
    public void display() {
        System.out.println("Model: " + model);
        System.out.println("Price: £" + price);
        System.out.println("Weight: " + weight + " grams");
        System.out.println("Size: " + size);
    }
}
