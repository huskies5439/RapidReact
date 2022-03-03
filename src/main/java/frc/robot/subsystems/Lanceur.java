
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Lanceur extends SubsystemBase {
  private final CANSparkMax moteurLanceurDroit = new CANSparkMax(27,MotorType.kBrushless);
  private final CANSparkMax moteurLanceurGauche = new CANSparkMax(38,MotorType.kBrushless);
  private final MotorControllerGroup moteurLanceur  = new MotorControllerGroup(moteurLanceurDroit,moteurLanceurGauche);
  private final SimpleMotorFeedforward lanceurFF = new SimpleMotorFeedforward(0.188,0.001412); // valider les coefficients
 private PIDController pid = new PIDController(0.003, 0, 0);//valider kP

 private ShuffleboardTab calibration = Shuffleboard.getTab("calibration");
 private NetworkTableEntry voltageLanceurCible =
       calibration.add("voltage lanceur cible",1)
             .getEntry();


  
  public Lanceur() {
    moteurLanceurDroit.setInverted(false);//vérifier
    moteurLanceurGauche.setInverted(true);//vérifier
    pid.setTolerance(125);//vérifier
    setConversionFactors(1.5);//vérifier avec le nouveau rapport d'engrenage
    moteurLanceurGauche.setIdleMode(IdleMode.kCoast);
    moteurLanceurDroit.setIdleMode(IdleMode.kCoast);
  }

  @Override
  public void periodic() {

   SmartDashboard.putNumber("Vitesse Lanceur", getVitesse());
   SmartDashboard.putBoolean("Bonne vitesse", estBonneVitesse());
   SmartDashboard.putNumber("Position", getPosition());
  }
  
  public void setConversionFactors(double facteur) {
      moteurLanceurDroit.getEncoder().setPositionConversionFactor(facteur);
      moteurLanceurGauche.getEncoder().setPositionConversionFactor(facteur);
      moteurLanceurDroit.getEncoder().setVelocityConversionFactor(facteur);
      moteurLanceurGauche.getEncoder().setVelocityConversionFactor(facteur);
  }
  
  public boolean estBonneVitesse() {
    return pid.atSetpoint();
  }

  public void setVoltage(double voltage) {
    moteurLanceur.setVoltage(voltage);
  }

  public double getVitesse() {
    return (moteurLanceurDroit.getEncoder().getVelocity() + moteurLanceurGauche.getEncoder().getVelocity())/2.0 ;
  }

  public double getPosition() {
    return (moteurLanceurDroit.getEncoder().getPosition() + moteurLanceurGauche.getEncoder().getPosition())/2.0;
  }
  
  public void stop() {
    setVoltage(0);
  }

  public void setVitesseFeedForwardPID(double vcible) {
    setVoltage(pid.calculate(getVitesse(),vcible)+lanceurFF.calculate(vcible));
  }

  public double getVoltageShuffleboard() {
    return voltageLanceurCible.getDouble(0);
  }



}