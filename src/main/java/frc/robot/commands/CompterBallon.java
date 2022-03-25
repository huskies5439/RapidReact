package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Convoyeur;

public class CompterBallon extends CommandBase {
  Convoyeur convoyeur;

  public CompterBallon() {}

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(convoyeur.capteurHaut()) {
      Constants.ballons +
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
