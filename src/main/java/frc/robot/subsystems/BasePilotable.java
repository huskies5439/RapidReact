package frc.robot.subsystems;
import java.io.IOException;
import java.util.function.DoubleSupplier;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BasePilotable extends SubsystemBase {
  //Moteurs
  private WPI_TalonFX moteurAvantG = new WPI_TalonFX(1);
  private WPI_TalonFX moteurArriereG = new WPI_TalonFX(2);
  private WPI_TalonFX moteurAvantD = new WPI_TalonFX(3);
  private WPI_TalonFX moteurArriereD = new WPI_TalonFX(4);

  private MotorControllerGroup moteursG = new MotorControllerGroup(moteurAvantG, moteurArriereG);
  private MotorControllerGroup moteursD = new MotorControllerGroup(moteurAvantD, moteurArriereD);

  private DifferentialDrive drive = new DifferentialDrive(moteursG, moteursD);

  //Encodeurs, gyro et odométrie
  private Encoder encodeurG = new Encoder(0, 1, true);
  private Encoder encodeurD = new Encoder(2, 3, false); 
  private double conversionEncodeur;

  private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
 
  private DifferentialDriveOdometry odometry;

  //Transmission
  private DoubleSolenoid pistonTransmission = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 5, 6);
  private boolean isHighGear = false;

  //FeedForward & PID en rotation
  private SimpleMotorFeedforward tournerFF = new SimpleMotorFeedforward(0.8, 0.05);
  private ProfiledPIDController tournerPID = new ProfiledPIDController(0.3, 0, 0, new TrapezoidProfile.Constraints(45, 45));
  
  //Calibration
  private ShuffleboardTab calibration = Shuffleboard.getTab("calibration");
  private NetworkTableEntry voltageBasePilotable = calibration.add("voltage base pilotable",0).getEntry();

public BasePilotable() {
  //Resets initiaux
  resetEncodeur();
  resetGyro();  

  //Ramp et brake
  setRamp(Constants.kRampTeleOp);
  setBrake(false);

  //transmission
  lowGear();

  //Inversion des moteurs
  moteurAvantG.setInverted(true);
  moteurArriereG.setInverted(true);
  moteurAvantD.setInverted(false);
  moteurArriereD.setInverted(false);

  //Configuration des encodeurs externes
  conversionEncodeur=Math.PI*Units.inchesToMeters(6)/(256*3*54/30); //roue de 6", ratio 54/30:1 shaft-roue 3:1 encodeur-shaft encodeur 256 clic encodeur
  encodeurG.setDistancePerPulse(conversionEncodeur);
  encodeurD.setDistancePerPulse(conversionEncodeur);

  //Odometrie
  odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getAngle()));

  //Tourner PID
  tournerPID.enableContinuousInput(-180, 180);
  tournerPID.setTolerance(Constants.kToleranceRotation);

}

  @Override
  public void periodic() {
    //Insertion d'informations dans le dashboard
    odometry.update(Rotation2d.fromDegrees(getAngle()), getPositionG(), getPositionD());
    //SmartDashoard
    //SmartDashboard.putNumberArray("odometry", getOdometry());  
    SmartDashboard.putNumber("Vitesse Moyenne", getVitesse());
    //SmartDashboard.putNumber("Vitesse Droite", getVitesseD());
    //SmartDashboard.putNumber("Vitesse Gauche", getVitesseG());  
    //SmartDashboard.putNumber("Position Moyenne", getPosition());
    //SmartDashboard.putNumber("Position Droite", getPositionD());
    //SmartDashboard.putNumber("Position Gauche", getPositionG());
    SmartDashboard.putNumber("Gyro", getAngle());
    //SmartDashboard.putNumber("GyroSpeed", getAngleSpeed());

    
  }
  ////////////////////////////////////////Moteurs & Drive/////////////////////////////////////////////
   public void conduire(double vx, double vz){
    drive.arcadeDrive(-0.85*vx, 0.7*vz);
  }

  public void autoConduire(double voltGauche, double voltDroit){
    moteursG.setVoltage(voltGauche);
    moteursD.setVoltage(voltDroit);
    drive.feed();
  } 
  
  public void stop(){
    drive.arcadeDrive(0, 0);
  }
  //Ramp
  public void setRamp(double ramp){
    moteurAvantG.configOpenloopRamp(ramp);
    moteurArriereG.configOpenloopRamp(ramp);
    moteurAvantD.configOpenloopRamp(ramp);
    moteurArriereD.configOpenloopRamp(ramp);
  } 
  //Mode brake
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
  

////////////////////////////////////////Encodeurs/////////////////////////////////////////////////////
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
    return (getVitesseD() + getVitesseG()) / 2.0;
  }
  
  public double getVoltageShuffleBoard(){
    return voltageBasePilotable.getDouble(0.0); 
  }
  
  public void resetEncodeur() {
    encodeurD.reset();
    encodeurG.reset();
  }
  
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getVitesseG(), getVitesseD());
  }

////////////////////////////////////////Transmission//////////////////////////////////////////////////

public boolean getIsHighGear(){
  return isHighGear;
} 

public void highGear(){
  pistonTransmission.set(DoubleSolenoid.Value.kForward);

  isHighGear = true;
}

public void lowGear(){
  pistonTransmission.set(DoubleSolenoid.Value.kReverse);

  isHighGear = false;
}

////////////////////////////////////////Gyro//////////////////////////////////////////////////////////

  public double getAngle() {
    return -gyro.getAngle();
  }

  public double getAngleSpeed() {
    return -gyro.getRate();
  } 

  public void resetGyro() {
    gyro.reset();
  } 
 
 
 

//////////////////////////////////////// Pose et Odométrie/////////////////////////////////////////////

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

  public void resetOdometry(Pose2d pose) {
    resetEncodeur();
    resetGyro();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getAngle()));
  }

////////////////////////////////////////Trajectory////////////////////////////////////////////////////

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

////////////////////////////////////////Ramsete///////////////////////////////////////////////////////

  public Command ramseteSimple(Trajectory trajectoire){
    //                                                                          //?? Maybe ajouter intialisation de la pose
    RamseteCommand ramseteCommand = new RamseteCommand(                         //On crée notre Ramsete Command
      trajectoire,                                                              //On passe notre trajectoire a la RamseteCommand afin qu'il sache quoi faire
      this::getPose,                                                            //On passe notre pose, afin qu'il connaisse son emplacement
      new RamseteController(2, 0.7),                                            //Ce sont des arguments réputés comme idéale pour des robots de FRC dans un Ramsete Controller
      new SimpleMotorFeedforward(Constants.kSRamsete, Constants.kVRamsete, Constants.kARamsete),  //On donnes nos arguments de FeedForward
      Constants.kinematics,                                                     //Notre kinematics (Afin que le robot conaisse ses mesures)
      this::getWheelSpeeds,                                                     //Donne nos vitesses de roues
      new PIDController(Constants.kPRamsete, 0, 0),                             //On donne un PID Controller a chacune des roues (SpeedController Group)
      new PIDController(Constants.kPRamsete, 0, 0),                             //"                                                                     "
      this::autoConduire,                                                       //On lui donne une commande pour conduire
      this);                                                                    //Tous les "this" sont la pour spécifier qu'on parle de la BasePilotable
      return ramseteCommand.andThen(()->stop());                                //On demande au robot de s'arrêter à la fin de la trajectoire
  }

////////////////////////////////////////PID de rotation///////////////////////////////////////////////
  public double getVoltagePIDF(double angleCible, DoubleSupplier mesure){
    return tournerPID.calculate(mesure.getAsDouble(), angleCible)+tournerFF.calculate(tournerPID.getSetpoint().velocity);
  }

  public boolean atAngleCible(){
    return tournerPID.atGoal();
   }
}
  














  
 



  

  

 

  

  

  
  
 

  

  

  
  
