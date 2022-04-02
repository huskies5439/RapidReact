package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.LimeLight;

public class TournerLimelight extends CommandBase {
  BasePilotable basePilotable;
  LimeLight limelight;
  double voltage;

  public TournerLimelight(BasePilotable basePilotable, LimeLight limelight) {
    this.basePilotable = basePilotable;
    this.limelight = limelight;
    addRequirements(basePilotable);
    addRequirements(limelight);
  }

  @Override
  public void initialize() {
    limelight.ledOn();
    basePilotable.setBrake(true);
  }

  @Override
  public void execute() {

    if(limelight.getTv()){
      voltage = basePilotable.getVoltagePIDF(0, limelight::getTx);
      basePilotable.autoConduire(-voltage, voltage);
    }

    else{
      basePilotable.stop();
    }
  }

  @Override
  public void end(boolean interrupted) {
    basePilotable.stop();
    limelight.ledOff();
  }

  @Override
  public boolean isFinished() {
    return false;
    //return basePilotable.atAngleCible();
  }
}
