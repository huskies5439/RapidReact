
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AutoGrimper;
import frc.robot.commands.Conduire;
import frc.robot.commands.ConvoyerFancy;
import frc.robot.commands.Gober;
import frc.robot.commands.Grimper;
import frc.robot.commands.LancerSimple;
import frc.robot.commands.ViserLancer;
import frc.robot.commands.auto.Auto1Ballon;
import frc.robot.commands.auto.Auto2Ballons;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Grimpeur;
import frc.robot.subsystems.Lanceur;
import frc.robot.subsystems.LimeLight;


public class RobotContainer {
  private final BasePilotable basePilotable = new BasePilotable();
  private final Gobeur gobeur = new Gobeur();
  private final Lanceur lanceur = new Lanceur();
  private final Convoyeur convoyeur = new Convoyeur();
  private final Grimpeur grimpeur = new Grimpeur();
  private final LimeLight limelight = new LimeLight();

  XboxController pilote = new XboxController(0);

  //Trajets
  private final SendableChooser<Command> chooser = new SendableChooser<>();
  private final Command Auto1Ballon = new Auto1Ballon(basePilotable, gobeur, lanceur, limelight, convoyeur);
  private final Command Auto2Ballons = new Auto2Ballons(basePilotable, gobeur, lanceur, limelight, convoyeur);
  //private final Command Auto3Ballons = new Auto3Ballons(basePilotable, gobeur, lanceur, limelight, convoyeur);( Auto 3 Ballon Fonctionne Pas ;(
  private final Command trajetVide = new WaitCommand(14);


  public class GobeurTrigger extends Trigger {
    @Override
    public boolean get() {
      return pilote.getRightTriggerAxis()>0.9;
    }
  }


  public RobotContainer() {
    //Chooser
    SmartDashboard.putData(chooser);
    chooser.setDefaultOption("Trajet Vide", trajetVide);
    chooser.addOption("Trajet 1 Ballon", Auto1Ballon);
    chooser.addOption("Trajet 2 Ballons", Auto2Ballons);
    //chooser.addOption("Trajet 3 Ballons", Auto3Ballons);
    
    configureButtonBindings();
    basePilotable.setDefaultCommand(new Conduire(pilote::getLeftY,pilote::getRightX, basePilotable));
    convoyeur.setDefaultCommand(new ConvoyerFancy(convoyeur));
  }

///////////////////////////////////////////////////////Buttons Bindings//////////////////////////////////////////////////////////////////////
  private void configureButtonBindings() {

    /*//B = Convoyer pour test
    new JoystickButton(pilote, Button.kB.value).toggleWhenPressed(new ConvoyerSimple(convoyeur, lanceur));*/

    //A = LancerSimple
    new JoystickButton(pilote, Button.kA.value).toggleWhenPressed(new LancerSimple(Constants.vitesseLancerBas, lanceur, convoyeur));

    //Y = Lancer en haut
    new JoystickButton(pilote, Button.kY.value).toggleWhenPressed(new ViserLancer(basePilotable, lanceur, convoyeur, limelight));

    //Trigger droit = Gober
    new GobeurTrigger().whileActiveContinuous(new Gober(gobeur));
    //Ajuster grimper
    new JoystickButton(pilote, Button.kStart.value).whenHeld(new Grimper(pilote::getLeftY, grimpeur, basePilotable));

    //POV Button haut/bas pour monter et descendre automatiquement le grimpeur
    new POVButton(pilote,0).toggleWhenPressed(new AutoGrimper(320000, grimpeur) );
    new POVButton(pilote, 180).toggleWhenPressed(new AutoGrimper(0, grimpeur) );
  }

///////////////////////////////////////////////////////Autonomous Command///////////////////////////////////////////////////////////////
    public Command getAutonomousCommand() {
      return chooser.getSelected();
      

     //return new CaracteriserDrive(basePilotable);

      //return new CaracteriserLanceur(lanceur, convoyeur);
      //return trajetVide;
      
  }
}
