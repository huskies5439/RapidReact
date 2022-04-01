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
