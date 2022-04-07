
package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public final class Constants {
    //Ramsete
    public static final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0.63); 
    public static final double kSRamsete = 0.7;//0.83 les commentaires sont les valeurs 
    public static final double kVRamsete = 1.25;//0.8 qui fonctionnaient le jeudi 30 mars
    public static final double kARamsete = 0.05;//0

    public static final double kPRamsete = 0.01;//1
    //Teleop
    public static final double kRampTeleOp  = 0.25;

    //Lancer
    public static final int vitesseLancerBas = 2000;

    //Alignement limelight
    public static final double kToleranceRotation = 3;

    

}
