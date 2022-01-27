// Copyright (c) FIRST and other WPILib contributors. 
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.IOException;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BasePilotable extends SubsystemBase {
//TODO Changer les numéros de moteurs
private WPI_TalonFX moteurAvantG = new WPI_TalonFX(1);
private WPI_TalonFX moteurArriereG = new WPI_TalonFX(2);
private WPI_TalonFX moteurAvantD = new WPI_TalonFX(3);
private WPI_TalonFX moteurArriereD = new WPI_TalonFX(4);

private MotorControllerGroup moteursG = new MotorControllerGroup(moteurAvantG, moteurArriereG);
private MotorControllerGroup moteursD = new MotorControllerGroup(moteurAvantD, moteurArriereD);

private DifferentialDrive drive = new DifferentialDrive(moteursG, moteursD);
private DifferentialDriveOdometry odometry;

private Encoder encodeurG = new Encoder(0, 1,false);
private Encoder encodeurD = new Encoder(2, 3,true);

private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

private Trajectory trajectoire = new Trajectory();

private DoubleSolenoid vitesse = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0,1); //les ports sont à valider

private enum RapportTransmission {

  LOW, HIGH 
} 
private RapportTransmission rapport=RapportTransmission.LOW; 


  public BasePilotable() {

    resetEncodeur();
    resetGyro();
    basseVitesse();

    setRamp(0.25);
    setBrake(false);
    moteurAvantG.setInverted(false);
    moteurArriereG.setInverted(false);
    moteurAvantD.setInverted(false);
    moteurArriereD.setInverted(false);
  }

  @Override
  public void periodic() {
  SmartDashboard.putNumber("Vitesse Moyenne", getVitesse());
  SmartDashboard.putNumber("Position Moyenne", getPosition());  
  SmartDashboard.putNumber("Vitesse Droite", getVitesseD());
  SmartDashboard.putNumber("Vitesse Gauche", getVitesseG());  
  SmartDashboard.putNumber("Position Droite", getPositionD());
  SmartDashboard.putNumber("Position Gauche", getPositionG());  


  SmartDashboard.putNumber("Gyro", getAngle());
  SmartDashboard.putNumber("GyroSpeed", getAngleSpeed());
  
  }

  public void conduire(double vx, double vz){
    //TODO Multiplieur du vx et vz à vérifier selon la conduite
    drive.arcadeDrive(-0.8*vx, 0.65*vz);
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

  public void setBrake(boolean isBrake) {
    if (isBrake) {
      moteurAvantD.setNeutralMode(NeutralMode.Brake);
      moteurArriereD.setNeutralMode(NeutralMode.Brake);
      moteurAvantG.setNeutralMode(NeutralMode.Brake);
      moteurArriereG.setNeutralMode(NeutralMode.Brake);
    }
    else {
      moteurAvantD.setNeutralMode(NeutralMode.Coast);
      moteurArriereD.setNeutralMode(NeutralMode.Coast);
      moteurAvantG.setNeutralMode(NeutralMode.Coast);
      moteurArriereG.setNeutralMode(NeutralMode.Coast);
    }
  }

  public double getPositionG() {
    return encodeurG.getDistance();
  }

  public double getPositionD(){
    return encodeurD.getDistance();
  } 

  public double getPosition(){
    return (getPositionG()+getPositionD())/2.0;
  }

  public double getVitesseD() {
    return encodeurD.getRate();
  }

  public double getVitesseG() {
    return encodeurG.getRate();
  }
  public double getVitesse() {
    return (getVitesseD() + getVitesseG()) / 2;
  }

  public void resetEncodeur() {
    encodeurD.reset();
    encodeurG.reset();
  }

  public double getAngle() {
    return gyro.getAngle();
  }

  public double getAngleSpeed() {
    return gyro.getRate();
  }

  public void resetGyro() {
    gyro.reset();
  } 

  public void hauteVitesse(){
    vitesse.set(DoubleSolenoid.Value.kReverse);
  }

  public void basseVitesse(){
    vitesse.set(DoubleSolenoid.Value.kForward);
  }

  public DoubleSolenoid.Value getRapport(){
    return vitesse.get();
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public double[] getOdometry() {
    double[] position = new double[3];
    double x = getPose().getTranslation().getX();
    double y = getPose().getTranslation().getY();
    double theta = getPose().getRotation().getDegrees();
    position[0] = x;
    position[1] = y;
    position[2] = theta;
    return position;
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getVitesseG(), getVitesseD());
  }

  public void resetOdometry(Pose2d pose) {
    resetEncodeur();
    resetGyro();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getAngle()));
  }

  public Trajectory creerTrajectoire(String trajet){
    String trajetJSON = "output/"+trajet+".wpilib.json";
    try{
      var path = Filesystem.getDeployDirectory().toPath().resolve(trajetJSON);
      return TrajectoryUtil.fromPathweaverJson(path);
    }
    catch(IOException e){
      DriverStation.reportError("Unable to open trajectory : " + trajetJSON, e.getStackTrace());
      return null;
    }
  }

  public void hauteVitesse(){
    vitesse.set(DoubleSolenoid.Value.kReverse);

    rapport=RapportTransmission.HIGH;
  }

  public void basseVitesse(){
    vitesse.set(DoubleSolenoid.Value.kForward);

    rapport=RapportTransmission.LOW;
  }

  public RapportTransmission getRapport(){
    return rapport;
  }
}
