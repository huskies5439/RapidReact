// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BasePilotable;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TrajetAuto extends SequentialCommandGroup {
  /** Creates a new TrajetAuto. */
  public TrajetAuto(String trajet, BasePilotable basePilotable) {
    Trajectory trajectoire = basePilotable.creerTrajectoire(trajet);
    addCommands(
      new InstantCommand(() -> basePilotable.resetOdometry(trajectoire.getInitialPose())),
      new InstantCommand(() -> basePilotable.setBrake(false)),
      new InstantCommand(() -> basePilotable.setRamp(0)),
      basePilotable.ramseteSimple(trajectoire).withTimeout(50)
    );
  }
}
