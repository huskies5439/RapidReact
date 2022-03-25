
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Conduire;
import frc.robot.commands.ConvoyerSimple;
import frc.robot.commands.Gober;
import frc.robot.commands.Grimper;
import frc.robot.commands.LancerFancy;
import frc.robot.commands.LancerSimple;
import frc.robot.commands.TournerAuto;
import frc.robot.commands.TournerLimelight;
import frc.robot.commands.caracteriser.CaracteriserDrive;
import frc.robot.commands.caracteriser.CaracteriserLanceur;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Grimpeur;
import frc.robot.subsystems.Lanceur;
import frc.robot.subsystems.LimeLight;
import frc.robot.commands.auto.Auto1Ballon;
import frc.robot.commands.auto.Auto2Ballons;
import frc.robot.commands.auto.Auto3Ballons;


public class RobotContainer {
  private final BasePilotable basePilotable = new BasePilotable();
  private final Gobeur gobeur = new Gobeur();
  private final Lanceur lanceur = new Lanceur();
  private final Convoyeur convoyeur = new Convoyeur();
  private final Grimpeur grimpeur = new Grimpeur();
  private final LimeLight limelight = new LimeLight();

  XboxController pilote = new XboxController(0);

  private final SendableChooser<Command> chooser = new SendableChooser<>();
  //trajets
  private final Command Auto1Ballon = new Auto1Ballon(basePilotable, gobeur, lanceur, limelight, convoyeur);
  private final Command Auto2Ballons = new Auto2Ballons(basePilotable, gobeur, lanceur, limelight, convoyeur);
  private final Command Auto3Ballons = new Auto3Ballons(basePilotable, gobeur, lanceur, limelight, convoyeur);
  private final Command trajetVide = new WaitCommand(14);


  public class GrimpeurTrigger extends Trigger {
    @Override
    public boolean get() {
      return pilote.getRightTriggerAxis()>0.9;
    }
  }


  public RobotContainer() {
    configureButtonBindings();
    chooser.setDefaultOption("Trajet Vide", trajetVide);
    chooser.addOption("Trajet 1 Ballon", Auto1Ballon);
    chooser.addOption("Tajet 2 Ballons", Auto2Ballons);
    chooser.addOption("Trajet 3 Ballons", Auto3Ballons);

    
    SmartDashboard.putData(chooser);

    basePilotable.setDefaultCommand(new Conduire(pilote::getLeftY,pilote::getRightX, basePilotable));
    convoyeur.setDefaultCommand(new ConvoyerFancy(convoyeur));


  }


  private void configureButtonBindings() {
    //A = Gober
    new JoystickButton(pilote, Button.kA.value).toggleWhenPressed(new Gober(gobeur));
  
    //B = Convoyer
    new JoystickButton(pilote, Button.kA.value).toggleWhenPressed(new ConvoyerSimple(convoyeur, lanceur));

    //Y = Lancer en haut
    new JoystickButton(pilote, Button.kY.value).toggleWhenPressed(new ViserLancer(basePilotable, lanceur, convoyeur, limelight));//pas la bonne vitesse

    //X = LancerSimple
    new JoystickButton(pilote, Button.kX.value).toggleWhenPressed(new LancerSimple(Constants.vitesseLancerBas, lanceur, convoyeur));



    //Trigger droit + joystick droit
    new GrimpeurTrigger().whileActiveContinuous(new Grimper(pilote::getRightY, grimpeur, basePilotable));


    //Bumper gauche/droite pour monter et descendre automatiquement le grimpeur À TESTER
    new JoystickButton(pilote, Button.kRightBumper.value).toggleWhenPressed(new AutoGrimper(20000, grimpeur) );//trouver la vraie hauteur
    new JoystickButton(pilote, Button.kLeftBumper.value).toggleWhenPressed(new AutoGrimper(0, grimpeur) );
  }
  

    public Command getAutonomousCommand() {
      return chooser.getSelected().withTimeout(14.8)
      //prepare Teleop
      .andThen (new InstantCommand(() -> basePilotable.setBrake(false)))
      .andThen (new InstantCommand(() -> basePilotable.setRamp(0.25)));
      //return null;
  }
}
