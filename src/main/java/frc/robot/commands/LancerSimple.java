// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;

public class LancerSimple extends ParallelCommandGroup {
  
  public LancerSimple(double voltage, Lanceur lanceur, Convoyeur convoyeur) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new StartEndCommand(() -> lanceur.setVoltage(voltage), lanceur::stop, lanceur),

      new SequentialCommandGroup(new WaitCommand(1.25),new StartEndCommand(convoyeur::fournir, convoyeur::stop,convoyeur ))





    );
  }
}
