package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  Updatable observer = context.mock(Updatable.class);
  Calculator calculator = new Calculator(observer);

  private void checkUpdates(int i) {
    context.checking(new Expectations() {{
      exactly(i).of(observer).update(calculator);
    }});
  }

  @Test
  public void integerValueOnPress() {
    checkUpdates(1);
    calculator.pushInt(3);
    assertThat(calculator.getResult(), is("3"));
  }

  @Test
  public void addsTwoIntegers() {
    checkUpdates(3);
    calculator.pushInt(2);
    calculator.pushInt(4);
    calculator.pushOp('+');
    assertThat(calculator.getResult(), is("6"));
  }

  @Test
  public void subtractSecondIntegerFromFirst() {
    checkUpdates(3);
    calculator.pushInt(1);
    calculator.pushInt(3);
    calculator.pushOp('-');
    assertThat(calculator.getResult(), is("2"));
  }

  @Test
  public void multipliesTwoIntegers() {
    checkUpdates(3);
    calculator.pushInt(4);
    calculator.pushInt(4);
    calculator.pushOp('*');
    assertThat(calculator.getResult(), is("16"));
  }

  @Test
  public void clearsText() {
    checkUpdates(2);
    calculator.pushInt(3);
    calculator.clear();
    assertThat(calculator.getResult(), is("0"));
  }
}