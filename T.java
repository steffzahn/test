public class T implements IT
{

    public void ifunc()
    {
        System.out.println("ifunc");
    }
 
    private void run()
    {
        IT.sfunc();
        dfunc();
        ifunc();
    }
    
    public static void main(String[] args)
    {
        new T().run();
    }
}

