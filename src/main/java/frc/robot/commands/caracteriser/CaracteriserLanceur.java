package frc.robot.commands.caracteriser;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lanceur;

public class CaracteriserLanceur extends CommandBase {
  Lanceur lanceur;

  public CaracteriserLanceur(Lanceur lanceur) {
    this.lanceur = lanceur;
    addRequirements(lanceur);

  }
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    lanceur.setVoltage(lanceur.getVoltageShuffleboard());
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