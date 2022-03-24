package frc.robot.commands.caracteriser;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Lanceur;

public class CaracteriserLanceur extends CommandBase {
  Lanceur lanceur;
  Convoyeur convoyeur;

  public CaracteriserLanceur(Lanceur lanceur, Convoyeur convoyeur) {
    this.lanceur = lanceur;
    this.convoyeur = convoyeur;
    addRequirements(lanceur);
    addRequirements(convoyeur);
    

  }
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    //lanceur.setVoltage(lanceur.getValeurShuffleboard()); //Pour trouver coefficient feed foward valeur en volt
    lanceur.setVitesseFeedForwardPID(lanceur.getValeurShuffleboard());
    if (lanceur.estBonneVitesse()) {
      convoyeur.fournir();
    }
  else {
    convoyeur.stop();
  }

  }

  @Override
  public void end(boolean interrupted) {
    lanceur.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}