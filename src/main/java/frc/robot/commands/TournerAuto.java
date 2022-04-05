package frc.robot.commands;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BasePilotable;

///////////ATTENTION, les paramètres PID et FF ne permettent pas à cette Commande
///////////de fonctionner à plus de 15-20 degrés

public class TournerAuto extends CommandBase {
double angleCible;
BasePilotable basePilotable;
double voltage;
  public TournerAuto(double angleCible, BasePilotable basePilotable) {
    this.angleCible = angleCible;
    this.basePilotable = basePilotable;
    addRequirements(basePilotable);

  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    voltage = basePilotable.getVoltagePIDF(angleCible, basePilotable::getAngle);
    basePilotable.autoConduire(-voltage, voltage);
  }

  @Override
  public void end(boolean interrupted) {
    basePilotable.stop();
  }

  @Override
  public boolean isFinished() {
    return basePilotable.atAngleCible();
  }
}
