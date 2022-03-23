
package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;
import frc.robot.subsystems.LimeLight;

public class LancerFancy extends CommandBase {
  Convoyeur convoyeur;
  Lanceur lanceur;
  LimeLight limelight;
  double vitesse;
  boolean enHaut;
  boolean forcerLancerBas;
  boolean shoot;
  boolean pretLancer;
  public LancerFancy(boolean forcerLancerBas, Lanceur lanceur, Convoyeur convoyeur, LimeLight limelight) {
    this.lanceur = lanceur;
    this.convoyeur = convoyeur;
    this.limelight = limelight;
    addRequirements(lanceur);
    //addRequirements(convoyeur); 
  }

  @Override
  public void initialize() {
    shoot = false;
    enHaut = false;
    pretLancer = false;

  }


  @Override
  public void execute() {

    if(limelight.getTv()) { //s'il voit la cible
      if(limelight.getDistance() < 1.5) {
        shoot = true;
        enHaut = false;
      }

      else if(limelight.getDistance() < 3 && limelight.getDistance() >= 1.5) {
        shoot = true;
        enHaut = true;
      }

      else {
        shoot = false;
      }

    }
    else { // si il ne voit pas la cible
      if(forcerLancerBas) {
        shoot = true;
        enHaut = false;
      }

      else {
        shoot = false;
      }

    }

    if(shoot) {
      if(enHaut) {
        vitesse = (2614 * limelight.getDistance()) - 211;
        pretLancer = (lanceur.estBonneVitesse() && Math.abs(limelight.getTx())<Constants.kToleranceRotation) || convoyeur.capteurHaut(); 
      }

      else {
        vitesse = 2100;
        pretLancer = lanceur.estBonneVitesse() || convoyeur.capteurHaut();
      }

      lanceur.setVitesseFeedForwardPID(vitesse);
    
      if (pretLancer) {
          convoyeur.fournir();
      }

      else {
        convoyeur.stop();
      }
      
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
