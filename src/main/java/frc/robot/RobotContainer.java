
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Conduire;
import frc.robot.commands.Gober;
import frc.robot.commands.Grimper;
import frc.robot.commands.LancerFancy;
import frc.robot.commands.LancerSimple;
import frc.robot.commands.caracteriser.CaracteriserDrive;
import frc.robot.commands.caracteriser.CaracteriserLanceur;
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

  public class GrimpeurTrigger extends Trigger {
    @Override
    public boolean get() {
      return pilote.getRightTriggerAxis()>0.9;
    }
  }


  public RobotContainer() {
    
    configureButtonBindings();
    basePilotable.setDefaultCommand(new Conduire(pilote::getLeftY,pilote::getRightX, basePilotable));
    

  }


  private void configureButtonBindings() {
    //A = Gober
    new JoystickButton(pilote, Button.kA.value).toggleWhenPressed(new Gober(gobeur)
                .alongWith( new StartEndCommand(convoyeur::fournir, convoyeur::stop,convoyeur ))
              );

    //X = Lancer en bas
    new JoystickButton(pilote, Button.kX.value).toggleWhenPressed(new LancerFancy(2100, lanceur, convoyeur));

    //Y = Lancer en haut
    new JoystickButton(pilote, Button.kY.value).toggleWhenPressed(new LancerFancy(4500, lanceur, convoyeur));//pas la bonne vitesse

    //B = Convoyeur (temporaire)
    //new JoystickButton(pilote, Button.kB.value).toggleWhenPressed(new StartEndCommand(convoyeur::fournir,convoyeur::stop,convoyeur)); 

    //Trigger droit + joystick droit
    new GrimpeurTrigger().whenActive(new Grimper(pilote::getRightY, grimpeur, basePilotable));
    
  }
  

    public Command getAutonomousCommand() {

    
    //return new TrajetAuto("test", basePilotable);
    return new LancerFancy(5500 , lanceur, convoyeur);
    //return null;
  }
}
