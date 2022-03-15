package frc.robot.commands.auto;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.TournerLimelight;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Lanceur;

public class Auto3Ballons extends SequentialCommandGroup {

  public Auto3Ballons(BasePilotable basePilotable, Gobeur gobeur, Lanceur lanceur, Limelight limelight) {
  Trajectory trajet = basePilotable.creerTrajectoire("3ballons");
  addCommands(
    //Lancer
     //new TournerLimelight(basePilotable, limelight),
     new WaitCommand(1),
     new InstantCommand(() -> gobeur.gober(),
     new WaitCommand(1),
     new InstantCommand(() -> lanceur.setVitesseFeedForwardPID(1)),
  //initialisation
  new InstantCommand(() -> basePilotable.resetOdometry(trajet.getInitialPose())),
  new InstantCommand(() -> basePilotable.setRamp(0)),
  new InstantCommand(() -> basePilotable.setBrake(true)),
  //trajet
  basePilotable.ramseteSimple(trajet),//parallèle avec gobeur

  //Lancer
  new TournerLimelight(basePilotable, limelight),
  new WaitCommand(1),
  new InstantCommand(() -> lanceur.setVitesseFeedForwardPID(1)),
  new WaitCommand(1),
  new InstantCommand(() -> lanceur.setVitesseFeedForwardPID(1))
  );
}
}
