
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Grimpeur extends SubsystemBase {

  private WPI_TalonFX moteurGrimpeur = new WPI_TalonFX(6);
  
  public Grimpeur() {
    moteurGrimpeur.setInverted(false); //A vérifier
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

}
