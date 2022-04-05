package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Convoyeur;

//Cette commande sert à compter le nombre de ballons qui sont lancés
//Cela sert à terminer les lancers en autonome

public class CompterBallon extends CommandBase {
  Convoyeur convoyeur;
  int nombreBallons;
  boolean etatActuel;
  boolean etatPasse;
  int limiteBallons;

  public CompterBallon(int limiteBallons, Convoyeur convoyeur) {
    this.convoyeur = convoyeur;
    this.limiteBallons = limiteBallons;
  }

  @Override
  public void initialize() {
    nombreBallons = 0;
    etatActuel = true;
    etatPasse = true;
  }

  @Override
  public void execute() {
    etatActuel = convoyeur.capteurHaut();

    if (etatActuel != etatPasse) {
      if (etatActuel) {
        nombreBallons++;
      }
      
      etatPasse = etatActuel;

    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return nombreBallons >= limiteBallons;

  }
}
