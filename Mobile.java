import javax.swing.JOptionPane;

public class Mobile extends Gadget {
    private int callingCredit; // Calling credit in minutes.

    // Constructor that initializes the mobile-specific and inherited attributes.
    public Mobile(String model, double price, int weight, String size, int callingCredit) {
        super(model, price, weight, size); // Call to the superclass (Gadget) constructor.
        this.callingCredit = callingCredit; // Initialize the calling credit.
    }

    // Accessor method for calling credit.
    public int getCallingCredit() {
        return callingCredit;
    }

    // Method to add calling credit. Only positive amounts are allowed.
    public void addCredit(int credit) {
        if (credit > 0) {
            callingCredit += credit;
            JOptionPane.showMessageDialog(null, "Successfully added " + credit + " minutes. Total credit now: " + callingCredit + " minutes.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Please enter a positive amount of credit to add.");
        }
    }

    // Method to make a phone call, reduces the calling credit by the duration of the call if possible.
    public void makeCall(String phoneNumber, int duration) {
        if (duration > 0) {
            if (duration <= callingCredit) {
                callingCredit -= duration;
                JOptionPane.showMessageDialog(null, "Call made to " + phoneNumber + " for " + duration + " minutes. Remaining credit: " + callingCredit + " minutes.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Insufficient credit to make the call. Available credit: " + callingCredit + " minutes.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Call duration must be positive.");
        }
    }
}
