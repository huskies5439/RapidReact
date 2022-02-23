// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;

public class LancerSimple extends CommandBase {
  Lanceur lanceur;
  Convoyeur convoyeur;
  double voltage;

  public LancerSimple(double voltage, Lanceur lanceur, Convoyeur convoyeur) {
    this.lanceur = lanceur;
    this.convoyeur = convoyeur;
    this.voltage = voltage;
    addRequirements(lanceur);
    addRequirements(convoyeur);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //revoir la logique du convoyeur qui décolle le ballon de la roue
   
    lanceur.setVoltage(voltage);
    convoyeur.fournir();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    convoyeur.stop();
    lanceur.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
