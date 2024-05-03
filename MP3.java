import javax.swing.JOptionPane;

public class MP3 extends Gadget {
    private int availableMemory; // Memory in megabytes

    // Constructor that initializes the MP3 specific and inherited attributes.
    public MP3(String model, double price, int weight, String size, int availableMemory) {
        super(model, price, weight, size); // Call to the superclass (Gadget) constructor.
        this.availableMemory = availableMemory; // Initialize the available memory.
    }

    // Accessor method for available memory.
    public int getAvailableMemory() {
        return availableMemory;
    }

    // Method to download music, reducing the available memory if there is enough space.
    public void downloadMusic(int size) {
        if (size > 0) {
            if (size <= availableMemory) {
                availableMemory -= size;
                JOptionPane.showMessageDialog(null, "Download successful. " + size + " MB of music downloaded. Remaining memory: " + availableMemory + " MB.");
            } else {
                JOptionPane.showMessageDialog(null, "Download failed. Not enough memory to download " + size + " MB of music. Available memory: " + availableMemory + " MB.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Download size must be positive.");
        }
    }

    // Method to delete music, increasing the available memory.
    public void deleteMusic(int size) {
        if (size > 0) {
            availableMemory += size;
            JOptionPane.showMessageDialog(null, size + " MB of music deleted. Available memory now: " + availableMemory + " MB.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Delete size must be positive.");
        }
    }
}
