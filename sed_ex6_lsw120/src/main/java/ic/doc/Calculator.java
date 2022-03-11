package ic.doc;

import java.util.Stack;

class Calculator {

  private final Stack<Integer> calculatorStack = new Stack<>();
  private Updatable observer;

  public Calculator() {
  }

  public Calculator(Updatable observer) {
    this.observer = observer;
  }

  public void addObserver(Updatable observer) {
    this.observer = observer;
  }

  public void pushInt(int i) {
    calculatorStack.push(i);
    observer.update(this);
  }

  public void pushOp(char op) {
    if (calculatorStack.size() >= 2) {
      final int one = calculatorStack.pop();
      final int two = calculatorStack.pop();
      int result;
      switch (op) {
        case '+' -> result = one + two;
        case '-' -> result = one - two;
        case '*' -> result = one * two;
        default -> result = 0;
      }
      calculatorStack.push(result);
    }
    observer.update(this);

  }

  public String getResult() {
    return calculatorStack.peek().toString();
  }

  public void clear() {
    calculatorStack.clear();
    calculatorStack.push(0);
    observer.update(this);
  }
}
