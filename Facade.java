public class Facade {
    public static Facade facade;
    public static Facade getInstance()  {
        if (facade == null) {
            facade = new Facade();
          }
          return facade;
        }
        
}
