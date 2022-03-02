
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;

public class LancerSimple extends ParallelCommandGroup {
  
  public LancerSimple(double voltage, Lanceur lanceur, Convoyeur convoyeur) {
   
    addCommands(
      new StartEndCommand(() -> lanceur.setVoltage(voltage), lanceur::stop, lanceur),

      new SequentialCommandGroup(new WaitCommand(1.25),new StartEndCommand(convoyeur::fournir, convoyeur::stop,convoyeur ))





    );
  }
}
