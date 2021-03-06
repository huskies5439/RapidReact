
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Convoyeur extends SubsystemBase {
  private WPI_TalonSRX moteurConvoyeur = new WPI_TalonSRX(14);
  private DigitalInput capteurHaut = new DigitalInput(9);
  private DigitalInput capteurBas = new DigitalInput(7);

  public Convoyeur() {
    moteurConvoyeur.setInverted(true);
  }

  @Override
  public void periodic() {
    /*SmartDashboard.putBoolean("Capteur Infrarouge Haut", capteurHaut());
    SmartDashboard.putBoolean("Capteur Infrarouge Bas", capteurBas());*/
  }

  public void fournir() {
    moteurConvoyeur.setVoltage(12); 
  }

  public void sortir() {
    moteurConvoyeur.setVoltage(-12);
  }

  public void stop() {
    moteurConvoyeur.setVoltage(0);
  }

  public boolean capteurHaut() {
    return capteurHaut.get();
  }

  
  public boolean capteurBas() {
    return capteurBas.get();
  }
}
