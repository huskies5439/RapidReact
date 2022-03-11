
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;

public class LancerFancy extends CommandBase {
  Convoyeur convoyeur;
  Lanceur lanceur;
  int vitesse;
  public LancerFancy(int vitesse, Lanceur lanceur, Convoyeur convoyeur) {
    this.vitesse = vitesse;
    this.lanceur = lanceur;
    this.convoyeur = convoyeur;
    addRequirements(lanceur);
    addRequirements(convoyeur);
  }

  @Override
  public void initialize() {}


  @Override
  public void execute() {
    lanceur.setVitesseFeedForwardPID(vitesse);
    convoyeur.fournir();
      /*if (lanceur.estBonneVitesse()) {
          convoyeur.fournir();

      }

      else {
        convoyeur.stop();
      }*/
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
