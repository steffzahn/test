class gtest
{
    public static void main( String[] args)
    {
        System.out.println("Start");
        Group myGroup = new FloatGroup();
        Field myField = new FloatField();
        
        System.out.println("Group");
        Element zero = myGroup.zero();
        System.out.println(zero);
        Element a = myGroup.parse( args[0] );
        System.out.println(a);
        a = myGroup.plus( a,zero );
        System.out.println(a);
        Element b = myGroup.negative(a);
        System.out.println(b);
        System.out.println(myGroup.isZero(myGroup.plus(a,b)));
        
        System.out.println("Field");
        Element zerof = myField.zero();
        Element one = myField.one();
        System.out.println(zerof);
        System.out.println(one);
        Element af = myField.parse( args[1]);
        System.out.println(af);
        af = myField.plus(af,zerof );
        System.out.println(af);
        Element bf = myField.negative(af);        
        System.out.println(bf);
        System.out.println(myField.isZero(myField.plus(af,bf)));
        System.out.println(myField.multiply(af,bf));
        Element cf = myField.inverse(af);    
        System.out.println(cf);
        System.out.println(myField.multiply(cf,af));
    }
}