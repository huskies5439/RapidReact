// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Convoyeur extends SubsystemBase {
  private WPI_TalonSRX moteurConvoyeur = new WPI_TalonSRX(14);

  public Convoyeur() {
    moteurConvoyeur.setInverted(true);
  }

  @Override
  public void periodic() {

  }

  public void fournir() {
    moteurConvoyeur.setVoltage(6); // à configurer
  }

  public void sortir() {
    moteurConvoyeur.setVoltage(-6); // à configurer
  }

  public void stop() {
    moteurConvoyeur.setVoltage(0);
  }
}
