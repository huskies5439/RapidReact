
package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;
import frc.robot.subsystems.LimeLight;

public class LancerFancy extends CommandBase {
  Convoyeur convoyeur;
  Lanceur lanceur;
  LimeLight limelight;
  double vitesse;
  public LancerFancy(Lanceur lanceur, Convoyeur convoyeur, LimeLight limelight) {
    this.lanceur = lanceur;
    this.convoyeur = convoyeur;
    this.limelight = limelight;
    addRequirements(lanceur);
    //addRequirements(convoyeur);
  }

  @Override
  public void initialize() {}


  @Override
  public void execute() {

    if((limelight.getDistance() > 1.5 && limelight.getDistance() <= 3.0) && limelight.getTv()){
      vitesse = (2614 * limelight.getDistance()) - 211;

    }
    else if(limelight.getDistance()<1.5 || !limelight.getTv()){
      vitesse = 2100;

    }
    else{
      vitesse = 0;
    }
    

    lanceur.setVitesseFeedForwardPID(vitesse);
    if(vitesse != 0){
    
      if (lanceur.estBonneVitesse() || convoyeur.capteur()) {
          convoyeur.fournir();

      }

      else {
        convoyeur.stop();
      }
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
