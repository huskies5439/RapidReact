package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Grimpeur;

public class Grimper extends CommandBase {
  Grimpeur grimpeur;
  BasePilotable basePilotable;
  DoubleSupplier vitesse;
  public Grimper(DoubleSupplier vitesse, Grimpeur grimpeur, BasePilotable basePilotable) {
    this.grimpeur = grimpeur;
    this.basePilotable = basePilotable;
    this.vitesse = vitesse;
    addRequirements(grimpeur);
    addRequirements(basePilotable);
  }

  @Override
  public void initialize() {
   // grimpeur.debarrer();
  }

  @Override
  public void execute() {
    grimpeur.setVitesse(vitesse.getAsDouble()*-0.6);//- car le joystick Y est inversé
    basePilotable.stop();
  }

  @Override
  public void end(boolean interrupted) {
    grimpeur.stop();
   // grimpeur.barrer();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
