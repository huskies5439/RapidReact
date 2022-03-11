package frc.robot.commands.auto;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.TournerLimelight;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Lanceur;

public class Auto3Ballons extends SequentialCommandGroup {

    public Auto3Ballons(BasePilotable basePilotable, Lanceur lanceur, LimeLight limelight) {
    Trajectory one = basePilotable.creerTrajectoire("3b-1");
    addCommands(
    //initialisation
    new InstantCommand(() -> basePilotable.resetOdometry(one.getInitialPose())),
    new InstantCommand(() -> basePilotable.setRamp(0)),
    new InstantCommand(() -> basePilotable.setBrake(true)),
    //trajet
    basePilotable.ramseteSimple(one),
      //Lancer ici
     /* new TournerLimelight(basePilotable, limelight),
      new WaitCommand(1),
      new InstantCommand(() -> pince.fermerPince()),
      new WaitCommand(1),
      new InstantCommand(() -> pince.ouvrirPince())
      */
    );
  }
}