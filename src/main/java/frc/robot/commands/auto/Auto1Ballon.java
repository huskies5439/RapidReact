// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Gober;
import frc.robot.commands.LancerFancy;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Lanceur;

public class Auto1Ballon extends SequentialCommandGroup {

  public Auto1Ballon(BasePilotable basePilotable, Gobeur gobeur, Lanceur lanceur, Limelight limelight) {
    Trajectory trajet = basePilotable.creerTrajectoire("1ballon");
    addCommands(
      //initialisations
      new InstantCommand(() -> basePilotable.resetOdometry(trajet.getInitialPose())),
      new InstantCommand(() -> basePilotable.setRamp(0)),
      new InstantCommand(() -> basePilotable.setBrake(true)),
      //Lancer
      //new TournerLimelight(basePilotable, limelight),
      new WaitCommand(3.754),//Délai pour pas que les ballon s'entrechoquent
      new InstantCommand(() -> gobeur.gober()),
      new WaitCommand(1),
      new InstantCommand(() -> lanceur.setVitesseFeedForwardPID(1)),
      //trajet
      basePilotable.ramseteSimple(trajet),

      new WaitCommand(1)
    );
    
  }
}
