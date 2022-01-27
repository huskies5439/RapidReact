// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.io.FilenameFilter;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BasePilotable;

public class Conduire extends CommandBase {
  BasePilotable basePilotable;
  double avancer;
  double tourner;
  //Déclare et permet l'envoie des states dans le SmartDashboard.
  

  //}

  //private State state = State.AUTO;//Initialise la state en Auto.
  /** Creates a new Conduire. */
  public Conduire(double avancer, double tourner, BasePilotable basePilotable) {
    this.basePilotable = basePilotable;
    this.avancer = avancer;
    this.tourner = tourner;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    basePilotable.conduire(avancer, tourner);
    // À calibrer
   /*if(basePilotable.getShift() == false && basePilotable.getVitesse()>2){
      basePilotable.hauteVitesse();

      

   }
  else if(basePilotable.getShift() == true && basePilotable.getVitesse()<1){
      basePilotable.basseVitesse();


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
