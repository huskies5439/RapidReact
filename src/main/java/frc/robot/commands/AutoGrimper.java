// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grimpeur;

public class AutoGrimper extends CommandBase {
  Grimpeur grimpeur;
  int cible;
  int marge;
  boolean stop;


  public AutoGrimper(int cible, Grimpeur grimpeur) {
    this.cible = cible;
    this.grimpeur = grimpeur;
    addRequirements(grimpeur);
    marge = 1000;
    
  }

  @Override
  public void initialize() {
    stop = false;
  }

  @Override
  public void execute() {
    if (grimpeur.getPosition() > cible + marge) {
      grimpeur.setVitesse(-0.2);
    }

    else if (grimpeur.getPosition() < cible - marge) {
      grimpeur.setVitesse(0.2);
    }
    else {
      stop = true;
      grimpeur.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    grimpeur.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}
