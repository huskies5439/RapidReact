// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BasePilotable extends SubsystemBase {
// Changer les numéros de moteurs
private WPI_TalonFX moteurAvantG = new WPI_TalonFX(1);
private WPI_TalonFX moteurArriereG = new WPI_TalonFX(2);
private WPI_TalonFX moteurAvantD = new WPI_TalonFX(3);
private WPI_TalonFX moteurArriereD = new WPI_TalonFX(4);

private MotorControllerGroup moteursG = new MotorControllerGroup(moteurAvantG, moteurArriereG);
private MotorControllerGroup moteursD = new MotorControllerGroup(moteurAvantD, moteurArriereD);

private DifferentialDrive drive = new DifferentialDrive(moteursG, moteursD);

private Encoder encodeurG = new Encoder(0, 1,false);
private Encoder encodeurD = new Encoder(2, 3,true);

private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  
  public BasePilotable() {}

  @Override
  public void periodic() {
  
    SmartDashboard.putNumber("Gyro", getAngle());
    SmartDashboard.putNumber("GyroSpeed", getAngleSpeed());

  }

  public void conduire(double vx, double vz){
    drive.arcadeDrive(vx, vz);
  }

  public void autoConduire(double voltGauche, double voltDroit){
    moteursG.setVoltage(voltGauche);
    moteursD.setVoltage(voltDroit);
    drive.feed();
  }

  public void stop(){
    drive.arcadeDrive(0, 0);
  }

  public void setRamp(double ramp){
    moteurAvantG.configOpenloopRamp(ramp);
    moteurArriereG.configOpenloopRamp(ramp);
    moteurAvantD.configOpenloopRamp(ramp); 
    moteurArriereD.configOpenloopRamp(ramp);
  }

  public void setNeutralMode(NeutralMode mode){
    moteurAvantG.setNeutralMode(mode);
    moteurArriereG.setNeutralMode(mode);
    moteurAvantD.setNeutralMode(mode);
    moteurArriereD.setNeutralMode(mode);
  }

  public void getPositionG(){

  }  

  public void getPositionD(){

  } 

  public void resetEncoder() {

  }

  public void resetGyro() {

    gyro.reset();
  }

  public double getAngle() {

    return gyro.getAngle();
  }

  public double getAngleSpeed() {

    return gyro.getRate();
  }
}
