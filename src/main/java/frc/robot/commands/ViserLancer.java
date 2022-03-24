// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;
import frc.robot.subsystems.LimeLight;

public class ViserLancer extends ParallelCommandGroup {

  public ViserLancer(BasePilotable basePilotable, Lanceur lanceur, Convoyeur convoyeur, LimeLight limelight) {
  
    addCommands(
    new TournerLimelight(basePilotable, limelight),
    new LancerFancy(lanceur, convoyeur, limelight)
    );
  }
}
