package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.BasePilotable;

public class Conduire extends CommandBase {
  BasePilotable basePilotable;
  DoubleSupplier avancer;
  DoubleSupplier tourner;
  
  public Conduire(DoubleSupplier avancer, DoubleSupplier tourner, BasePilotable basePilotable) {
    this.basePilotable = basePilotable;
    this.avancer = avancer;
    this.tourner = tourner;
    addRequirements(basePilotable);
  }

  
  @Override
  public void initialize() {
    //S'assure d'avoir les bons paramètres pour la conduite en autonome
    basePilotable.setBrake(false);
    basePilotable.setRamp(Constants.kRampTeleOp);
  }

  
  @Override
  public void execute() {

    //Conduire en tant que tel
    basePilotable.conduire(avancer.getAsDouble(), tourner.getAsDouble());

  //Transmission automatique
  if(! basePilotable.getIsHighGear() && basePilotable.getVitesse()>1.65){
      basePilotable.highGear();

      

   }
  else if(basePilotable.getIsHighGear() && basePilotable.getVitesse()<1.25){
      basePilotable.lowGear();


  }
}


  @Override
  public void end(boolean interrupted) {
  }


  @Override
  public boolean isFinished() {
    return false;
  }
}
