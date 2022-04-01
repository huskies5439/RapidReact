package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Convoyeur;

public class ConvoyerFancy extends CommandBase {
  Convoyeur convoyeur;
  public ConvoyerFancy(Convoyeur convoyeur) {
    addRequirements(convoyeur);
    this.convoyeur = convoyeur;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(convoyeur.capteurHaut() && !convoyeur.capteurBas()){
      convoyeur.fournir();
    }

    else {
      convoyeur.stop();
    }
  }

  @Override
  public void end(boolean interrupted) {
    convoyeur.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
