package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Convoyeur;

public class CompterBallon extends CommandBase {
  Convoyeur convoyeur;
  int nombreBallons;
  boolean etatActuel;
  boolean etatPasse;
  int limiteBallons;

  public CompterBallon(int limite_ballons, Convoyeur convoyeur) {
    this.convoyeur = convoyeur;
    this.limiteBallons = limite_ballons;
  }

  @Override
  public void initialize() {
    etatActuel = false;
    etatPasse = false;
  }

  @Override
  public void execute() {
    etatActuel = convoyeur.capteurHaut();

    if (etatActuel != etatPasse) {
      if (etatActuel) {
        nombreBallons++;
      }
      SmartDashboard.putNumber("nombre de ballon", nombreBallons);

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
