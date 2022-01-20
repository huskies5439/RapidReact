// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.SubSystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class BasePilotable extends SubsystemBase {
// Changer les numéros de moteurs
private WPI_TalonFX moteurAvantGauche = new WPI_TalonFX(1);
private WPI_TalonFX moteurDerriereGauche = new WPI_TalonFX(2);
private WPI_TalonFX moteurAvantDroit = new WPI_TalonFX(3);
private WPI_TalonFX moteurDerriereDroit = new WPI_TalonFX(4);

private MotorControllerGroup moteursGauches = new MotorControllerGroup(moteurAvantGauche, moteurDerriereGauche);
private MotorControllerGroup moteursDroits = new MotorControllerGroup(moteurAvantDroit, moteurDerriereDroit);

private DifferentialDrive drive = new DifferentialDrive(moteursGauches, moteursDroits);

private Encoder encodeurGauche = new Encoder(0, 1,false);
private Encoder encodeurDroit = new Encoder(2, 3,true);

private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  /** Creates a new BasePilotable. */
  public BasePilotable() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void conduire(double vx, double vz){
drive.arcadeDrive(vx, vz);


  }
  public void autoConduire(double voltGauche, double voltDroit){
    moteursGauches.setVoltage(voltGauche);
    moteursDroits.setVoltage(voltDroit);
    drive.feed();
      }
}
