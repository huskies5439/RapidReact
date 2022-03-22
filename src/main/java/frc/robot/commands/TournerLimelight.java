// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;



import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.ledOn();

  }

  // Called every time the scheduler runs while the command is scheduled.
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

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    basePilotable.stop();
    limelight.ledOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    //return basePilotable.atAngleCible();
  }
}
