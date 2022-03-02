
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
  public void initialize() {}

  
  @Override
  public void execute() {

    basePilotable.conduire(avancer.getAsDouble(), tourner.getAsDouble());
    // À calibrer
  if(! basePilotable.getIsHighGear() && basePilotable.getVitesse()>1.65){
      basePilotable.highGear();

      

   }
  else if(basePilotable.getIsHighGear() && basePilotable.getVitesse()<1.25){
      basePilotable.lowGear();


  }
}


  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}
