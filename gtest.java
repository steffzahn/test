class gtest
{
    public static void main( String[] args)
    {
        System.out.println("Start");
        Group myGroup = FloatGroup.instance();
        
        GroupElement zero = myGroup.zero();
        System.out.println(zero);
        GroupElement a = myGroup.parse( args[0]);
        System.out.println(a);
        a = a.plus( zero );
        System.out.println(a);
        GroupElement b = a.inverse();        
        System.out.println(b);
        System.out.println(a.plus(b).isZero());
    }
}