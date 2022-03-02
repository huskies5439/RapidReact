
package frc.robot.commands.auto;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BasePilotable;


public class TrajetAuto extends SequentialCommandGroup {
  
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
