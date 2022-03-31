
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Grimpeur extends SubsystemBase {

  private WPI_TalonFX moteurGrimpeur = new WPI_TalonFX(6);
  private Servo barrure = new Servo(0);
  
  
  
  public Grimpeur() {
    moteurGrimpeur.setInverted(false); 
    moteurGrimpeur.setNeutralMode(NeutralMode.Brake);
    moteurGrimpeur.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    resetEncodeur();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Position Grimpeur", getPosition());

  }

  public void resetEncodeur() {
    moteurGrimpeur.setSelectedSensorPosition(0);
  }

  public double getPosition() {
    return moteurGrimpeur.getSelectedSensorPosition();
  }

  public void stop() {
    moteurGrimpeur.set(0.0);
  }


  public void setVitesse(double vitesse) {
    moteurGrimpeur.set(vitesse);
  }

  public void barrer() {
    barrure.setAngle(30);
  }

  public void debarrer(){
    barrure.setAngle(170);
  }

}
