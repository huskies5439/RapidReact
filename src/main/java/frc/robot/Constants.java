
package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class Constants {
    //Ramsete
    public static final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0.63); 
    public static final double kSRamsete = 0.83;//0.83
    public static final double kVRamsete = 0.8;//0.8
    public static final double kPRamsete = 1;//1

    //Teleop
    public static final double kRampTeleOp  = 0.25;

    //Lancer
    public static final int vitesseLancerBas = 2200;

    //Alignement limelight
    public static final double kToleranceRotation = 2;

    

}
