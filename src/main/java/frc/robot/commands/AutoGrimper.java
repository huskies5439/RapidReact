package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grimpeur;

public class AutoGrimper extends CommandBase {
  Grimpeur grimpeur;
  int cible;
  int marge;
  boolean stop;


  public AutoGrimper(int cible, Grimpeur grimpeur) {
    this.cible = cible;
    this.grimpeur = grimpeur;
    addRequirements(grimpeur);
    marge = 1000;
    
  }

  @Override
  public void initialize() {
    stop = false;
  }

  @Override
  public void execute() {
    if (grimpeur.getPosition() > cible + marge) {
      grimpeur.setVitesse(-0.3);//descendre
    }

    else if (grimpeur.getPosition() < cible - marge) {
      grimpeur.setVitesse(0.7);//monter
    }
    else {
      stop = true;
      grimpeur.stop();
    }
  }

  @Override
  public void end(boolean interrupted) {
    grimpeur.stop();
  }

  @Override
  public boolean isFinished() {
    return stop;
  }
}
