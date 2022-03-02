
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Conduire;
import frc.robot.commands.Gober;
import frc.robot.commands.LancerSimple;
import frc.robot.subsystems.BasePilotable;
import frc.robot.subsystems.Convoyeur;
import frc.robot.subsystems.Gobeur;
import frc.robot.subsystems.Grimpeur;
import frc.robot.subsystems.Lanceur;


public class RobotContainer {
  private final BasePilotable basePilotable = new BasePilotable();
  private final Gobeur gobeur = new Gobeur();
  private final Lanceur lanceur = new Lanceur();
  private final Convoyeur convoyeur = new Convoyeur();
  private final Grimpeur grimpeur = new Grimpeur();

  XboxController pilote = new XboxController(0);


  public RobotContainer() {
    
    configureButtonBindings();
    basePilotable.setDefaultCommand(new Conduire(pilote::getLeftY,pilote::getRightX, basePilotable));
    

  }


  private void configureButtonBindings() {
    //A = Gober
    new JoystickButton(pilote, Button.kA.value).whenHeld(new Gober(gobeur)
                .alongWith( new StartEndCommand(convoyeur::fournir, convoyeur::stop,convoyeur )));

    //X = Lancer
    new JoystickButton(pilote, Button.kX.value).toggleWhenPressed(new LancerSimple(7, lanceur, convoyeur));

    //B = Convoyeur (temporaire)
    new JoystickButton(pilote, Button.kB.value).toggleWhenPressed(new StartEndCommand(convoyeur::fournir,convoyeur::stop,convoyeur)); 

    //RightBumper = Monter grimpeur
    new JoystickButton(pilote, Button.kRightBumper.value).whenHeld(new StartEndCommand(grimpeur::monter,grimpeur::stop,grimpeur));

    //LeftBumper = Descendre grimpeur
    new JoystickButton(pilote, Button.kLeftBumper.value).whenHeld(new StartEndCommand(grimpeur::descendre,grimpeur::stop,grimpeur));
  }
  

    public Command getAutonomousCommand() {

    
    //return new TrajetAuto("test", basePilotable);
    return null;
  }
}
