public interface IT
{
    void ifunc();

    default void dfunc()
    {
        System.out.println("dfunc");
    }

    static void sfunc(){
        System.out.println("sfunc");
    }
}

