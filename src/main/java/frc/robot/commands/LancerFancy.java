package frc.robot.commands;



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

    if(limelight.getTv()) { //Si on voit la cible, on décide le comportement selon la distance
      if(limelight.getDistance() < 1.5) {//proche = on lance en bas
        shoot = true;
        enHaut = false;
      }

      else if(limelight.getDistance() < 2.75 && limelight.getDistance() >= 1.5) {//zone où l'angle permet de lancer en haut
        shoot = true;
        enHaut = true;
      }

      else {//trop loin = on ne lance pas
        shoot = false;
      }

    }
    else { // pas de cible = on lance pas
      shoot = false;
    }

    if(shoot) { //Lancer.....
      if(enHaut) { //Lancer en haut
        //trouver rpm du lanceur selon la distance
        vitesse = 184 * Math.pow(limelight.getDistance(), 2) -237 * limelight.getDistance() + 3229 + 50; 
         //lance si bonne vitesse et centrer sur la limelight
        pretLancer = (lanceur.estBonneVitesse() && Math.abs(limelight.getTx())<Constants.kToleranceRotation) || convoyeur.capteurHaut(); 
      }

      else { //sinon on lance en bas
        vitesse = Constants.vitesseLancerBas;
        //Ici, on ne vérifie pas l'alignement car la limelight est trop proche pour être précise
        pretLancer = lanceur.estBonneVitesse() || convoyeur.capteurHaut();
      }

      //Pousser la vitesse voulue dans le PID+Feedforward
      lanceur.setVitesseFeedForwardPID(vitesse);
    
      if (pretLancer) { //s'il est prêt à lancer, on convoit
          convoyeur.fournir();
      }

      else {
        convoyeur.stop();
      }
      
    }
    else { //s'il ne lance pas
      lanceur.stop();
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
