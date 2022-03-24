
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Grimpeur extends SubsystemBase {

  private WPI_TalonFX moteurGrimpeur = new WPI_TalonFX(6);
  private Servo barrure = new Servo(0);
  
  
  public Grimpeur() {
    moteurGrimpeur.setInverted(true); 
    moteurGrimpeur.setNeutralMode(NeutralMode.Brake);
    
  }

  @Override
  public void periodic() {

  }
  public void stop() {
    moteurGrimpeur.set(0.0);
  }

  public void monter() {
    moteurGrimpeur.set(1.0);
  }

  public void descendre() {
    moteurGrimpeur.set(-1.0);
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
