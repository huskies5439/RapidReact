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
      //Si le capteur du bas voit un ballon, on convoit jusqu'à ce qu'il soit assez rentré pour ne plus bloqué le capteur
      //Si on bloque le capteur du haut, on ne convoit plus du tout car le ballon du haut toucherait au lanceur
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
