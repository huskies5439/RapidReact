package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {

  private NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
  private NetworkTable limelight = networkTableInstance.getTable("limelight-huskies");
  private NetworkTableEntry tv = limelight.getEntry("tv");
  private NetworkTableEntry tx = limelight.getEntry("tx");
  private NetworkTableEntry ta = limelight.getEntry("ta");
  private NetworkTableEntry ty = limelight.getEntry("ty");
  private NetworkTableEntry ledMode = limelight.getEntry("ledMode");
  private NetworkTableEntry stream = limelight.getEntry("stream");
  //TODO Trouver les bonnes valeurs
  
  double hLimelight = 0;
  double hCible = 0; 
  double angleLimelight = 0;

  public LimeLight() {
    ledOff();
    stream.setNumber(0);

  }

  public double getDistance(){
    return(hCible-hLimelight)/Math.tan(Math.toRadians(angleLimelight+getTy()));
  }

  public double getTa() {
    return ta.getDouble(0);
  }

  public boolean getTv() {
    return tv.getDouble(0)==1;
  }

  public double getTx() {
    return tx.getDouble(0);
  }

  public double getTy() {
    return ty.getDouble(0);
  }


  public void ledOn() {
    ledMode.setNumber(3);
  }
  public void ledOff() {
    ledMode.setNumber(1);
  }



  @Override
  public void periodic() {
    SmartDashboard.putNumber("tx", getTx());
    SmartDashboard.putNumber("ty", getTy());
    SmartDashboard.putNumber("distance limelight", getDistance());
  }
}
