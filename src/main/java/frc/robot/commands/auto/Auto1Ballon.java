// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BasePilotable;


public class Auto1Ballon extends SequentialCommandGroup {

  public Auto1Ballon() {
 /* Trajectory trajet = BasePilotable.creerTrajectoire("1ballon");
  addCommands(
    new InstantCommand(() -> basePilotable.resetOdometry(trajet.getInitialPose())),
    new InstantCommand(() -> basePilotable.setRamp(0)),
    new WaitCommand(3.754),//Délai pour pas que les ballon s'entrechoquent
    new InstantCommand(() -> pince.fermerPince()),
    new WaitCommand(1),
    new InstantCommand(() -> pince.ouvrirPince()),
      //trajet
      basePilotable.ramseteSimple(trajet),

      new WaitCommand(1)      new InstantCommand(() -> basePilotable.setBrake(true)),
      //Lancer
      //new TournerLimelight(basePilotable, limelight),

  );*/
  }
}
