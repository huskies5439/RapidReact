
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gobeur extends SubsystemBase {
  private DoubleSolenoid bras = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2); // À déterminer
  private WPI_TalonFX rouleau = new WPI_TalonFX(5);

  public Gobeur() {
    rouleau.setInverted(true);//à déterminer
    brasIn();
    stop();
    
  }

  @Override
  public void periodic() {
    
  }

  public void stop() {
    rouleau.set(0.0);
  }

  public void gober() {
    rouleau.set(0.5);
  }

  public void jeter() {
    rouleau.set(-0.5);
  }

  public void brasIn() {
    bras.set(Value.kForward);
  }

  public void brasOut() {
    bras.set(Value.kReverse);
  }

}
