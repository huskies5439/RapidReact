// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.SubSystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class BasePilotable extends SubsystemBase {
// Changer les numéros de moteurs
private WPI_TalonFX moteurAvantGauche = new WPI_TalonFX(1);
private WPI_TalonFX moteurDerriereGauche = new WPI_TalonFX(2);
private WPI_TalonFX moteurAvantDroit = new WPI_TalonFX(3);
private WPI_TalonFX moteurDerriereDroit = new WPI_TalonFX(4);

  /** Creates a new BasePilotable. */
  public BasePilotable() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
