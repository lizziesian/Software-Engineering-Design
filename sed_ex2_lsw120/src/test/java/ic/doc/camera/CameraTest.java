package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  Sensor sensor = context.mock(Sensor.class);
  MemoryCard memoryCard = context.mock(MemoryCard.class);
  byte[] data = new byte[]{1, 2, 3};
  Camera camera = new Camera(sensor, memoryCard);


  @Test
  public void switchingTheCameraOnPowersUpTheSensor() {

    context.checking(new Expectations() {{
      exactly(1).of(sensor).powerUp();
    }});
    camera.powerOn();
  }

  @Test
  public void switchingTheCameraOffPowersDownTheSensor() {

    context.checking(new Expectations() {{
      exactly(1).of(sensor).powerDown();
    }});
    camera.powerOff();
  }

  @Test
  public void shutterDoesNothingWhenPowerIsOff() {

    context.checking(new Expectations() {{
      never(sensor);
      never(memoryCard);
    }});
    camera.pressShutter();
  }

  @Test
  public void shutterCopiesDataFromSensorToMemoryCardWhenPowerOn() {

    context.checking(new Expectations() {{
      exactly(1).of(sensor).powerUp();
      exactly(1).of(sensor).readData();
      will(returnValue(data));
      exactly(1).of(memoryCard).write(data);
    }});
    camera.powerOn();
    camera.pressShutter();
  }

  @Test
  public void sensorDoesNotPowerDownWhenDataIsBeingWrittenAndCameraIsOff() {

    context.checking(new Expectations() {{
      ignoring(sensor).powerUp();
      exactly(1).of(sensor).readData();
      will(returnValue(data));
      oneOf(memoryCard).write(data);
      never(sensor).powerDown();
    }});
    camera.powerOn();
    camera.pressShutter();
    camera.powerOff();
  }

  @Test
  public void onceWritingDataCompleteSensorPowerDown() {

    context.checking(new Expectations() {{
      ignoring(sensor).powerUp();
      exactly(1).of(sensor).readData();
      will(returnValue(data));
      oneOf(memoryCard).write(data);
      oneOf(sensor).powerDown();
    }});
    camera.powerOn();
    camera.pressShutter();
    camera.powerOff();
    camera.writeComplete();
  }
}
