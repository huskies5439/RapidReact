package frc.robot.commands.auto;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.CompterBallon;
import frc.robot.commands.Gober;
import frc.robot.commands.ViserLancer;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Lanceur;

public class Auto3Ballons extends SequentialCommandGroup {

  public Auto3Ballons(BasePilotable basePilotable, Gobeur gobeur, Lanceur lanceur, LimeLight limelight, Convoyeur convoyeur) {
  Trajectory trajet = basePilotable.creerTrajectoire("3ballons");
  addCommands(
  //0. Initialisations -> Créer un CommandGroup, c'est pareil dans tous les trajets??
  new InstantCommand(() -> basePilotable.resetOdometry(trajet.getInitialPose())),
  new InstantCommand(() -> basePilotable.setRamp(0)),
  new InstantCommand(() -> basePilotable.setBrake(true)),

  //1. Lancer le ballon pré-chargé en haut
  new ViserLancer(basePilotable, lanceur, convoyeur, limelight)
  .raceWith(new CompterBallon(1,convoyeur)),//arrêter le ViserLancer après que 1 ballon soit lancé


  //2. Avancer et Gober pour ramasser les deux ballons
  new ParallelRaceGroup(//Race fait que Gober va s'arrêter automatiquement à la fin du trajet
          basePilotable.ramseteSimple(trajet),
          new Gober(gobeur)),



  //3. Continuer la trajectoire pour revenir dans le bon sens
    //Le ParallelRaceGroup s'en occupe

  //4. Lancer les deux ballons en haut
  new ViserLancer(basePilotable, lanceur, convoyeur, limelight)
      .raceWith(new CompterBallon(2,convoyeur))//arrêter le ViserLancer après que 2 ballon soit lancé
  );
}
}
