// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gobeur extends SubsystemBase {
  private DoubleSolenoid bras = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0); // À déterminer
  private WPI_TalonFX rouleau = new WPI_TalonFX(1); //TODO À déterminer

  public Gobeur() {
    
  }

  @Override
  public void periodic() {
    
  }

  public void stop() {
    rouleau.set(0.0);
  }

  public void gober() {
    rouleau.set(1.0);
  }

  public void jeter() {
    rouleau.set(-1.0);
  }

  public void brasIn() {
    bras.set(Value.kReverse);
  }

  public void brasOut() {
    bras.set(Value.kForward);
  }

}
