// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.io.FilenameFilter;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BasePilotable;

public class Conduire extends CommandBase {
  BasePilotable basePilotable;
  double avancer;
  double tourner;
  //Déclare et permet l'envoie des states dans le SmartDashboard.
  /*private enum State {
    LOW(0), HIGH(1), AUTO(2);

    private int stateValue;

    State(int stateValue) {
      this.stateValue = stateValue;
    }*/

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
    
    /*if (RobotState.isAutonomous()){
      state= State.AUTO; //Pas nécéssaire en compé, mais sert a retourner en Autonomous.
     } 
   else if (Math.abs(basePilotable.getVitesse()) > 2 && state == State.LOW) { 
     basePilotable.hauteVitesse(); 
     state =State.HIGH; //Vérifier que le Robot est en basseVitesse pour passer en hauteVitesse.
     } 
   else if (Math.abs(basePilotable.getVitesse()) < 1.2 && state == State.HIGH) {
     basePilotable.basseVitesse(); 
     state = State.LOW; //Vérifier que le Robot est en hauteVitesse pour passer en basseVitesse.
     } 
   else if (state == State.AUTO) {
     basePilotable.basseVitesse(); //Force la basseVitesse en Autonomous.
     if (!RobotState.isAutonomous()) { 
       state = State.LOW; //Après Autonomous passe dans la StateMachine principal.
       } 
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
