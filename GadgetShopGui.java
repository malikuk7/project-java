import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GadgetShopGui extends JFrame implements ActionListener {
    private ArrayList<Gadget> gadgets;
    private JTextField modelField, priceField, weightField, sizeField;
    private JTextField creditField, memoryField, displayNumberField;
    private JTextField phoneField, durationField, downloadField;
    private JButton addMobileButton, addMP3Button, clearButton, displayAllButton;
    private JButton makeACallButton, downloadMusicButton;
    private JComboBox<String> mobileSelector;
    private JComboBox<String> mp3Selector;

    public GadgetShopGui() {
        gadgets = new ArrayList<>();
        initializeComponents();
        setFrameProperties();
    }

    private void initializeComponents() {
        setLayout(new GridLayout(0, 2, 10, 10)); // Use GridLayout for better alignment

        add(new JLabel("Model:"));
        modelField = new JTextField(10);
        add(modelField);

        add(new JLabel("Price:"));
        priceField = new JTextField(10);
        add(priceField);

        add(new JLabel("Weight:"));
        weightField = new JTextField(10);
        add(weightField);

        add(new JLabel("Size:"));
        sizeField = new JTextField(10);
        add(sizeField);

        add(new JLabel("Credit:"));
        creditField = new JTextField(10);
        add(creditField);

        add(new JLabel("Memory:"));
        memoryField = new JTextField(10);
        add(memoryField);

        add(new JLabel("Phone No:"));
        phoneField = new JTextField(10);
        add(phoneField);

        add(new JLabel("Duration:"));
        durationField = new JTextField(10);
        add(durationField);

        add(new JLabel("Download:"));
        downloadField = new JTextField(10);
        add(downloadField);

        add(new JLabel("Display No.:"));
        displayNumberField = new JTextField(10);
        add(displayNumberField);

        mobileSelector = new JComboBox<>();
        updateMobileSelector(); 
        add(new JLabel("Select Mobile:"));
        add(mobileSelector);

        mp3Selector = new JComboBox<>();
        updateMP3Selector();
        add(new JLabel("Select MP3:"));
        add(mp3Selector);

        addMobileButton = new JButton("Add Mobile");
        addMobileButton.addActionListener(this);
        add(addMobileButton);

        addMP3Button = new JButton("Add MP3");
        addMP3Button.addActionListener(this);
        add(addMP3Button);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        add(clearButton);

        displayAllButton = new JButton("Display All");
        displayAllButton.addActionListener(this);
        add(displayAllButton);

        makeACallButton = new JButton("Make A Call");
        makeACallButton.addActionListener(this);
        add(makeACallButton);

        downloadMusicButton = new JButton("Download Music");
        downloadMusicButton.addActionListener(this);
        add(downloadMusicButton);
    }

    private void setFrameProperties() {
        setTitle("Gadget Shop");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        try {
            if (command.equals("Add Mobile")) {
                addMobile();
            } else if (command.equals("Add MP3")) {
                addMP3();
                updateMP3Selector();
            } else if (command.equals("Clear")) {
                clearFields();
            } else if (command.equals("Display All")) {
                displayAllGadgets();
            } else if (command.equals("Make A Call")) {
                makeACall();
            } else if (command.equals("Download Music")) {
                downloadMusic();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Incorrect data format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addMobile() {
        String model = modelField.getText();
        double price = Double.parseDouble(priceField.getText());
        int weight = Integer.parseInt(weightField.getText());
        String size = sizeField.getText();
        int credit = Integer.parseInt(creditField.getText());
        Mobile newMobile = new Mobile(model, price, weight, size, credit);
        gadgets.add(newMobile);
        updateMobileSelector();  // Refresh the mobile list in dropdown
        JOptionPane.showMessageDialog(this, "Mobile Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addMP3() {
        String model = modelField.getText();
        double price = Double.parseDouble(priceField.getText());
        int weight = Integer.parseInt(weightField.getText());
        String size = sizeField.getText();
        int memory = Integer.parseInt(memoryField.getText());
        MP3 newMP3 = new MP3(model, price, weight, size, memory);
        gadgets.add(newMP3);
        JOptionPane.showMessageDialog(this, "MP3 Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearFields() {
        modelField.setText("");
        priceField.setText("");
        weightField.setText("");
        sizeField.setText("");
        creditField.setText("");
        memoryField.setText("");
        phoneField.setText("");
        durationField.setText("");
        downloadField.setText("");
        displayNumberField.setText("");
    }

    private void displayAllGadgets() {
        JFrame frame = new JFrame("All Gadgets");
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gadgets.size(); i++) {
            Gadget gadget = gadgets.get(i);
            sb.append("Index: ").append(i).append("\n");
            sb.append("Model: ").append(gadget.getModel()).append("\n");
            sb.append("Price: £").append(gadget.getPrice()).append("\n");
            sb.append("Weight: ").append(gadget.getWeight()).append(" grams\n");
            sb.append("Size: ").append(gadget.getSize()).append("\n");
            if (gadget instanceof Mobile) {
                Mobile mobile = (Mobile) gadget;
                sb.append("Calling Credit: ").append(mobile.getCallingCredit()).append(" minutes\n");
            } else if (gadget instanceof MP3) {
                MP3 mp3 = (MP3) gadget;
                sb.append("Available Memory: ").append(mp3.getAvailableMemory()).append(" MB\n");
            }
            sb.append("\n");
        }

        textArea.setText(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateMP3Selector() {
        mp3Selector.removeAllItems();
        for (Gadget gadget : gadgets) {
            if (gadget instanceof MP3) {
                mp3Selector.addItem("Index: " + gadgets.indexOf(gadget) + " - " + gadget.getModel());
            }
        }
    }

    private void updateMobileSelector() {
        mobileSelector.removeAllItems();
        for (int i = 0; i < gadgets.size(); i++) {
            if (gadgets.get(i) instanceof Mobile) {
                Mobile mobile = (Mobile) gadgets.get(i);
                mobileSelector.addItem("Index: " + i + " - " + mobile.getModel() + " (" + mobile.getCallingCredit() + " mins)");
            }
        }
    }

    private void makeACall() {
        if (mobileSelector.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "No mobile selected or available", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selected = (String) mobileSelector.getSelectedItem();
        int index = Integer.parseInt(selected.split(" - ")[0].replace("Index: ", "").trim());  // Extract index from the selection

        if (index < 0 || index >= gadgets.size() || !(gadgets.get(index) instanceof Mobile)) {
            JOptionPane.showMessageDialog(this, "Invalid selection or not a mobile", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Mobile mobile = (Mobile) gadgets.get(index);
        String phoneNumber = phoneField.getText();
        int duration = Integer.parseInt(durationField.getText());

        if (phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number is empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (duration <= 0) {
            JOptionPane.showMessageDialog(this, "Invalid duration. Must be positive.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        mobile.makeCall(phoneNumber, duration);
        JOptionPane.showMessageDialog(this, "Call made successfully to " + phoneNumber + " for " + duration + " minutes.", "Success", JOptionPane.INFORMATION_MESSAGE);
        updateMobileSelector();  // Update selector to reflect any changes in credit after the call
    }

    private void downloadMusic() {
        try {
            int index = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the index of the MP3 player to use:"));
            if (index < 0 || index >= gadgets.size() || !(gadgets.get(index) instanceof MP3)) {
                JOptionPane.showMessageDialog(this, "Invalid index or not an MP3 player", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MP3 mp3 = (MP3) gadgets.get(index);
            int size = Integer.parseInt(downloadField.getText());
            mp3.downloadMusic(size);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(this, "Selected gadget is not an MP3", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateDisplayNumber() {
        try {
            int index = Integer.parseInt(displayNumberField.getText());
            if (index >= 0 && index < gadgets.size()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Display number is out of valid range!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid display number format!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GadgetShopGui::new);
    }
}
