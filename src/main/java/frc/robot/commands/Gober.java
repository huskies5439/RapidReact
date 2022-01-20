// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Gobeur;

public class Gober extends CommandBase {
  Gobeur gobeur;
  
  public Gober(Gobeur gobeur) {
    this.gobeur = gobeur; 
    addRequirements(gobeur);
  }
  
  @Override
  public void initialize() {
    gobeur.brasOut();
    gobeur.gober();
  }

  @Override
  public void execute() {
    
  }

  @Override
  public void end(boolean interrupted) {
    gobeur.stop();
    gobeur.brasIn();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
