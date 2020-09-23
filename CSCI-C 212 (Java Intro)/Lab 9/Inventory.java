import javax.swing.JFrame; //frame
import javax.swing.JPanel; //panel
import javax.swing.JButton; //button
import javax.swing.JLabel; //label
import javax.swing.JTextField; //text field
import javax.swing.event.*; //for DocumentListener and DocumentEvent
import java.awt.event.*; //for ActionListener and ActionEvent

/**
 * creates and displays an inventory for managing stuff
 */
public class Inventory extends JFrame {
  private JButton calculateButton;
  private JLabel cartonsPerShipmentLabel;
  private JLabel itemsPerCartonLabel;
  private JLabel totalLabel;
  private JTextField cartonsPerShipmentTextField;
  private JTextField itemsPerCartonTextField;
  private JTextField totalTextField; //uneditable!
  
  /**
   * instantiates
   */
  public Inventory() {
    
    //Suggested in book to have inner method
    class CalculateButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        try {
        long cartons = Integer.parseInt(cartonsPerShipmentTextField.getText());
        long items = Integer.parseInt(itemsPerCartonTextField.getText());
        totalTextField.setText("" + (cartons * items));
        } catch(NumberFormatException error) {
          totalTextField.setText("Use valid number");
        }
      }
    }
    
    //searched java database, found this gem
    class TextFieldDocumentListener implements DocumentListener {
      public void changedUpdate(DocumentEvent e) {
        clearText();
      }
      
      public void removeUpdate(DocumentEvent e) {
        clearText();
      }
      
      public void insertUpdate(DocumentEvent e) {
        clearText();
      }
      
      private void clearText() {
        totalTextField.setText("");
      }
    }
    
    JPanel panel = new JPanel();
    panel.setLayout(null); //not default!
    
    cartonsPerShipmentLabel = new JLabel("Cartons Per Shipment: ");
    itemsPerCartonLabel = new JLabel("Items Per Carton: ");
    totalLabel = new JLabel("Total: ");
    
    cartonsPerShipmentLabel.setBounds(5, 5, 145, 30);
    itemsPerCartonLabel.setBounds(5, 40, 145, 30);
    totalLabel.setBounds(210, 5, 240, 30);
    
    cartonsPerShipmentTextField = new JTextField();
    itemsPerCartonTextField = new JTextField();
    totalTextField = new JTextField("");
    totalTextField.setEditable(false);
    
    cartonsPerShipmentTextField.setBounds(150, 5, 50, 30);
    itemsPerCartonTextField.setBounds(150, 40, 50, 30);
    totalTextField.setBounds(260, 5, 140, 30);
    
    cartonsPerShipmentTextField.setHorizontalAlignment(4);
    itemsPerCartonTextField.setHorizontalAlignment(4);
    totalTextField.setHorizontalAlignment(4);
    
    cartonsPerShipmentTextField.getDocument().addDocumentListener(new TextFieldDocumentListener()); //because textfield is document, I don't personally want to use a keyboard listener becayse this seems better
    itemsPerCartonTextField.getDocument().addDocumentListener(new TextFieldDocumentListener()); //because textfield is document, I don't personally want to use a keyboard listener becayse this seems better
    
    calculateButton = new JButton("Calculate Total");
    calculateButton.addActionListener(new CalculateButtonListener());
    calculateButton.setBounds(210, 40, 200, 30);
    
    panel.add(cartonsPerShipmentLabel);
    panel.add(cartonsPerShipmentTextField);
    panel.add(itemsPerCartonLabel);
    panel.add(itemsPerCartonTextField);
    panel.add(totalLabel);
    panel.add(totalTextField);
    panel.add(calculateButton);
    this.add(panel);
    this.setSize(420, 100);
    this.setVisible(true);
    this.setTitle("Inventory");
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
  }
  
  public static void main(String[] args) {
    Inventory thisbetterwork = new Inventory();
  }
}
