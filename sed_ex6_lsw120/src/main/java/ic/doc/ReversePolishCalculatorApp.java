package ic.doc;

public class ReversePolishCalculatorApp {

  private final Calculator calculator = new Calculator();
  private final View observer = new View(calculator);

  public ReversePolishCalculatorApp() {
    calculator.addObserver(observer);
  }


  public static void main(String[] args) {
    new ReversePolishCalculatorApp();
  }
}
