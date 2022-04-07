// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.Constants;
import frc.robot.commands.CompterBallon;
import frc.robot.commands.LancerSimple;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;

public class AutoBasJusteLancer extends ParallelRaceGroup {
  public AutoBasJusteLancer(Lanceur lanceur,Convoyeur convoyeur) {
    addCommands(
    new LancerSimple(Constants.vitesseLancerBas, lanceur, convoyeur),
    new CompterBallon(1, convoyeur)

    );
  }
}
