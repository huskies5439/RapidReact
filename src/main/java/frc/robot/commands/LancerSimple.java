// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;

public class LancerSimple extends CommandBase {
  Lanceur lanceur;
  Convoyeur convoyeur;
  int vitesse;

  
  public LancerSimple(int vitesse, Lanceur lanceur, Convoyeur convoyeur) {
    this.lanceur = lanceur;
    this.convoyeur = convoyeur;
    this.vitesse = vitesse;
    addRequirements(lanceur);
    addRequirements(convoyeur);
  }

  
  @Override
  public void initialize() {}

  @Override
  public void execute() {

   lanceur.setVitesseFeedForwardPID(vitesse);

    if (lanceur.estBonneVitesse() && convoyeur.capteurHaut()) { 
        convoyeur.fournir();// On fournit jusqu'au capteur, ou jusqu'au lanceur s'il est à la bonne vitesse
    }

    else {
      convoyeur.stop();
    }
  }

  @Override
  public void end(boolean interrupted) {
    convoyeur.stop();
    lanceur.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
