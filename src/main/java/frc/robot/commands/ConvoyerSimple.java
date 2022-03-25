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

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    lanceur.setVitesseFeedForwardPID(1500);
    convoyeur.fournir();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    lanceur.stop();
    convoyeur.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
