package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lanceur;

public class CaracteriserDrive extends CommandBase {
  Lanceur lanceur;

  public CaracteriserDrive(Lanceur lanceur) {
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