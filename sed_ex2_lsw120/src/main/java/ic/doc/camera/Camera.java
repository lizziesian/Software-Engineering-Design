package ic.doc.camera;

public class Camera implements WriteListener {

  private final Sensor sensor;
  private final MemoryCard memoryCard;
  private boolean powerStatus;
  private boolean writeStatus;

  public Camera(Sensor sensor, MemoryCard memoryCard) {
    this.sensor = sensor;
    this.memoryCard = memoryCard;
    powerStatus = false;
    writeStatus = true;
  }

  public void pressShutter() {
    if (powerStatus) {
      byte[] data = sensor.readData();
      memoryCard.write(data);
      writeStatus = false;
    }
  }

  public void powerOn() {
    sensor.powerUp();
    powerStatus = true;
  }

  public void powerOff() {
    if (writeStatus) {
      sensor.powerDown();
    }
    powerStatus = false;
  }

  @Override
  public void writeComplete() {
    writeStatus = true;
    if (!powerStatus) {
      powerOff();
    }
  }
}

