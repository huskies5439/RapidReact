// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Grimpeur;

public class Grimper extends CommandBase {
  Grimpeur grimpeur;
  BasePilotable basePilotable;
  DoubleSupplier vitesse;
  public Grimper(DoubleSupplier vitesse, Grimpeur grimpeur, BasePilotable basePilotable) {
    this.grimpeur = grimpeur;
    this.basePilotable = basePilotable;
    this.vitesse = vitesse;
    addRequirements(grimpeur);
    addRequirements(basePilotable);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    grimpeur.debarrer();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    grimpeur.setVitesse(vitesse.getAsDouble()*0.6);
    basePilotable.stop();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    grimpeur.stop();
    grimpeur.barrer();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
