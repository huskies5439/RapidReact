
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Convoyeur extends SubsystemBase {
  private WPI_TalonSRX moteurConvoyeur = new WPI_TalonSRX(14);
  private DigitalInput capteurInfrarouge = new DigitalInput(9);

  public Convoyeur() {
    moteurConvoyeur.setInverted(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Capteur Infrarouge", capteur());
  }

  public void fournir() {
    moteurConvoyeur.setVoltage(12); // à configurer
  }

  public void sortir() {
    moteurConvoyeur.setVoltage(-12); // à configurer
  }

  public void stop() {
    moteurConvoyeur.setVoltage(0);
  }

  public boolean capteur() {
    return capteurInfrarouge.get();
  }
}
