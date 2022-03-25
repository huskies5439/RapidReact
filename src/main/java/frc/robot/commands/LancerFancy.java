
package frc.robot.commands;



import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  boolean shoot;
  boolean pretLancer;
  public LancerFancy(Lanceur lanceur, Convoyeur convoyeur, LimeLight limelight) {
    this.lanceur = lanceur;
    this.convoyeur = convoyeur;
    this.limelight = limelight;
    addRequirements(lanceur);
    addRequirements(convoyeur); 
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
      if(limelight.getDistance() < 1.5) {//proche
        shoot = true;
        enHaut = false;
      }

      else if(limelight.getDistance() < 3.25 && limelight.getDistance() >= 1.5) {//distance moyenne
        shoot = true;
        enHaut = true;
      }

      else {//trop loin
        shoot = false;
      }

    }
    else { // si il ne voit pas la cible
      shoot = false;
    }

    if(shoot) { //si il lance
      if(enHaut) { //si il lance en haut
        vitesse = 414 * Math.pow(limelight.getDistance(), 2) -1308 * limelight.getDistance() + 4666;
        pretLancer = (lanceur.estBonneVitesse() && Math.abs(limelight.getTx())<Constants.kToleranceRotation) || convoyeur.capteurHaut(); 
      }

      else { //sinon on lance en bas
        vitesse = Constants.vitesseLancerBas;
        pretLancer = lanceur.estBonneVitesse() || convoyeur.capteurHaut();
      }

      lanceur.setVitesseFeedForwardPID(vitesse);
    
      if (pretLancer) { //si il est prêt à lancer
          convoyeur.fournir();
      }

      else { //si il n'est pas prêt à lancer
        convoyeur.stop();
      }
      
    }
    else { //si il ne lance pas
      lanceur.stop();
      convoyeur.stop();
    }

    SmartDashboard.putNumber("Cible vitesse lanceur", vitesse);
    
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
