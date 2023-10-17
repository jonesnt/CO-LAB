import java.awt.*;  //  this might need to be changed down the road
/**
 * @author hadleya
 * This is designed to add simple customization to both the background of
 * the program, but also to tasks. 
*/
public class Customizer {
  private Color bgColor;

  /**
   * The Customizer constructor take no arguments. It is designed to be used
   * to modify using the . operator.
   * The default color is White.
   */
  public Customizer() {
    bgColor = Color.White;
  }

  /**
   * Change the background color.
   * @param color the color of the background
   */
  public void changeBG(Color color) {
    bgColor = color;
  }
  
  /**
   * Change the color of a specific task
   * @param color the color for the task
   * @param task the task that's getting it's color changed
   */
  public void changeTaskColor(Color color, Task task) {
    //  TASK HASNT BEEN IMPLEMENTED YET!!!
    //  This may need to change once it is
    task.setColor(color);
  }
}
