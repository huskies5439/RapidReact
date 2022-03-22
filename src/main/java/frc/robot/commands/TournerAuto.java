// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import com.fasterxml.jackson.databind.JsonSerializable.Base;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BasePilotable;

public class TournerAuto extends CommandBase {
double angleCible;
BasePilotable basePilotable;
double voltage;
  public TournerAuto(double angleCible, BasePilotable basePilotable) {
    this.angleCible = angleCible;
    this.basePilotable = basePilotable;
    addRequirements(basePilotable);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    voltage = basePilotable.getVoltagePIDF(angleCible, basePilotable::getAngle);
    basePilotable.autoConduire(-voltage, voltage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    basePilotable.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
