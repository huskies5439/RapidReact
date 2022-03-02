package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {

  private NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
  private NetworkTable limelight = networkTableInstance.getTable("limelight-huskies");

  private NetworkTableEntry ledMode = limelight.getEntry("ledMode");
  private NetworkTableEntry camMode = limelight.getEntry("camMode");

  public LimeLight() {
    ledOff();
    camHumain();

  }

  public void ledOn() {
    ledMode.setNumber(3);
  }
  public void ledOff() {
    ledMode.setNumber(1);
  }
  public void camHumain(){
    camMode.setNumber(1);
  }
  public void camDetection(){
    camMode.setNumber(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
