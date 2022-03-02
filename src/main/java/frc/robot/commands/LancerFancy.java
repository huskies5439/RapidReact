
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lanceur;

public class LancerFancy extends CommandBase {
  Lanceur lanceur;
  int vitesse;
  public LancerFancy(Lanceur lanceur, int vitesse) {
    this.lanceur = lanceur;
    this.vitesse = vitesse;
  }


  @Override
  public void initialize() {}


  @Override
  public void execute() {

    lanceur.setVitesseFeedForwardPID(vitesse);
  }
  

  @Override
  public void end(boolean interrupted) {

    lanceur.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
