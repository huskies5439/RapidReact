// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.CompterBallon;
import frc.robot.commands.LancerSimple;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Lanceur;
import frc.robot.subsystems.LimeLight;


public class AutoBas1Ballon extends SequentialCommandGroup {
  
  public AutoBas1Ballon(BasePilotable basePilotable, Gobeur gobeur, Lanceur lanceur, LimeLight limelight, Convoyeur convoyeur) {
   
   Trajectory trajet = basePilotable.creerTrajectoire("1ballon");
  
   addCommands(
    new InstantCommand(() -> basePilotable.resetOdometry(trajet.getInitialPose())),
    new InstantCommand(() -> basePilotable.setRamp(0)),
    new InstantCommand(() -> basePilotable.setBrake(true)), 
    
      new LancerSimple(Constants.vitesseLancerBas+2000, lanceur, convoyeur)
      .raceWith(new CompterBallon(1,convoyeur)),

      basePilotable.ramseteSimple(trajet),

      new InstantCommand(() -> basePilotable.setBrake(false)),
      new InstantCommand(() -> basePilotable.setRamp(Constants.kRampTeleOp))

    );
  

   
    
  





  }
}
