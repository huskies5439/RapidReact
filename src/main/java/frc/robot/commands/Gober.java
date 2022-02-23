// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Gobeur;

public class Gober extends CommandBase {
  Convoyeur convoyeur;
  Gobeur gobeur;
  
  public Gober(Gobeur gobeur, Convoyeur convoyeur) {//remettre la dépendance du convoyeur
    this.convoyeur = convoyeur;
    this.gobeur = gobeur; 
    addRequirements(convoyeur);
    addRequirements(gobeur);
  }
  
  @Override
  public void initialize() {
    gobeur.brasOut();
    gobeur.gober();
    convoyeur.fournir();
  }

  @Override
  public void execute() {
    
  }

  @Override
  public void end(boolean interrupted) {
    gobeur.stop();
    gobeur.brasIn();
    convoyeur.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
