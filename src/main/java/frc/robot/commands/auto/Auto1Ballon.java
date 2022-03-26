// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;


import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.CompterBallon;
import frc.robot.commands.ViserLancer;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Lanceur;
import frc.robot.subsystems.LimeLight;

public class Auto1Ballon extends SequentialCommandGroup {

  public Auto1Ballon(BasePilotable basePilotable, Gobeur gobeur, Lanceur lanceur, LimeLight limelight, Convoyeur convoyeur) {
    
    Trajectory trajet = basePilotable.creerTrajectoire("1ballon");

    addCommands(
      //0. Initialisations -> Créer un CommandGroup, c'est pareil dans tous les trajets??
      new InstantCommand(() -> basePilotable.resetOdometry(trajet.getInitialPose())),
      new InstantCommand(() -> basePilotable.setRamp(0)),
      new InstantCommand(() -> basePilotable.setBrake(true)),
      
      //1. Attendre pour éviter de cogner les ballons 
      new WaitCommand(3.754),//~3 secondes c'est peut-être beaucoup

      //2. Lancer un ballon en haut
      new ViserLancer(basePilotable, lanceur, convoyeur, limelight)
          .raceWith(new CompterBallon(1,convoyeur)),//arrêter le ViserLancer après que 1 ballon soit lancé

      //3. Attendre pour laisser le temps aux robots de bouger
      new WaitCommand(7),// à tester, il faut s'assurer de ne pas manquer de temps
    
      //4. Recule pour le 2 points
      basePilotable.ramseteSimple(trajet)
    );
    
  }
}
