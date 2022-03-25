
package frc.robot.commands;

import com.fasterxml.jackson.databind.node.ShortNode;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;

public class LancerSimple extends ParallelCommandGroup {
  Lanceur lanceur;
  Convoyeur convoyeur;
  boolean ready;
  boolean shoot;
  double vitesse;
  
  public LancerSimple(double voltage, Lanceur lanceur, Convoyeur convoyeur) {
    this.lanceur = lanceur;
    this.convoyeur = convoyeur;
    addRequirements(lanceur);
    addRequirements(convoyeur);
   
    addCommands(
      new StartEndCommand(() -> lanceur.setVitesseFeedForwardPID(voltage), lanceur::stop, lanceur),

      new SequentialCommandGroup(new WaitCommand(1.25),new StartEndCommand(convoyeur::fournir, convoyeur::stop,convoyeur))
    );
  }
}
