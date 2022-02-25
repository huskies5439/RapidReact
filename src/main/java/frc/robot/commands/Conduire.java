// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BasePilotable;

public class Conduire extends CommandBase {
  BasePilotable basePilotable;
  DoubleSupplier avancer;
  DoubleSupplier tourner;
  
  public Conduire(DoubleSupplier avancer, DoubleSupplier tourner, BasePilotable basePilotable) {
    this.basePilotable = basePilotable;
    this.avancer = avancer;
    this.tourner = tourner;
    addRequirements(basePilotable);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    basePilotable.conduire(avancer.getAsDouble(), tourner.getAsDouble());
    // À calibrer
  /*if(! basePilotable.getIsHighGear() && basePilotable.getVitesse()>4){
      basePilotable.highGear();

      

   }
  else if(basePilotable.getIsHighGear() && basePilotable.getVitesse()<2){
      basePilotable.lowGear();


  }*/
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
