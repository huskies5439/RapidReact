// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsytems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gobeur extends SubsystemBase {

  private DoubleSolenoid tiroir = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0); //a determiner

  private  

  public Gobeur() {
    
  }

  @Override
  public void periodic() {
    @Override
    public void moteurStop() {
      rouleau.set(0.0);
    }

    public void moteurGobe() {
      rouleau.set(1.0);
    }

    public void moteurPanic() {
      rouleau.set(-1.0);
    }

    public void tiroirIn() {
      tiroir.set(Value.kReverse);
    }

    public void tiroirOut() {
      tiroir.set(Value.kForward);
    }

  }
}
