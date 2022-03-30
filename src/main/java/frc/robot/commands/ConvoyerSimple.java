// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;

public class ConvoyerSimple extends CommandBase {
  Convoyeur convoyeur;
  Lanceur lanceur;
  public ConvoyerSimple(Convoyeur convoyeur, Lanceur lanceur) {
    this.convoyeur = convoyeur;
    this.lanceur = lanceur;
    addRequirements(convoyeur);
    addRequirements(lanceur);
  }
  
  @Override
  public void initialize() {}

  @Override
  public void execute() { // Lancer un ballon à 1500 RPM
    lanceur.setVitesseFeedForwardPID(1500);
    convoyeur.fournir();
  }

  @Override
  public void end(boolean interrupted) {
    lanceur.stop();
    convoyeur.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
