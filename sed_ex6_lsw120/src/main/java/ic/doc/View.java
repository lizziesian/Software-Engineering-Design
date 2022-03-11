package ic.doc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class View implements Updatable {

  private final JTextField textField = new JTextField(10);

  public View(Calculator calculator) {
    JFrame frame = new JFrame("Reverse Polish Calculator");
    frame.setSize(350, 200);
    textField.setText("0");

    JPanel panel = new JPanel();

    JButton button1 = new JButton("1");
    button1.addActionListener(actionEvent -> calculator.pushInt(1));
    panel.add(button1);

    JButton button2 = new JButton("2");
    button2.addActionListener(actionEvent -> calculator.pushInt(2));
    panel.add(button2);

    JButton button3 = new JButton("3");
    button3.addActionListener(actionEvent -> calculator.pushInt(3));
    panel.add(button3);

    JButton button4 = new JButton("4");
    button4.addActionListener(actionEvent -> calculator.pushInt(4));
    panel.add(button4);

    JButton buttonPlus = new JButton("+");
    buttonPlus.addActionListener(actionEvent -> calculator.pushOp('+'));
    panel.add(buttonPlus);

    JButton buttonMinus = new JButton("-");
    buttonMinus.addActionListener(actionEvent -> calculator.pushOp('-'));
    panel.add(buttonMinus);

    JButton buttonMultiply = new JButton("*");
    buttonMultiply.addActionListener(actionEvent -> calculator.pushOp('*'));
    panel.add(buttonMultiply);

    JButton buttonClear = new JButton("Clear");
    buttonClear.addActionListener(actionEvent -> calculator.clear());
    panel.add(buttonClear);

    panel.add(textField);

    frame.getContentPane().add(panel);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public void update(Calculator calculator) {
    textField.setText(calculator.getResult());
  }
}
