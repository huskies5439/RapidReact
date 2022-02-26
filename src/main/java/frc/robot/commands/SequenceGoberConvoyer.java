// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Gobeur;


public class SequenceGoberConvoyer extends ParallelCommandGroup {

  public SequenceGoberConvoyer(Gobeur gobeur, Convoyeur convoyeur) {

    
    addCommands(
        new Gober(gobeur),

        new StartEndCommand(convoyeur::fournir, convoyeur::stop,convoyeur )
    );
  }
}
