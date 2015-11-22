import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class gtest
{
    private static void groupTest(Group myGroup, String v1 )
    {
        System.out.println("Group "+myGroup.getClass().getName() );
        Element zero = myGroup.zero();
        System.out.println(zero);
        Element a = myGroup.parse( v1 );
        System.out.println(a);
        a = myGroup.add( a,zero );
        System.out.println(a);
        Element b = myGroup.negative(a);
        System.out.println(b);
        System.out.println(myGroup.isZero(myGroup.add(a,b)));
        System.out.println(myGroup.minus(a,b));
        
    }
    
    private static void fieldTest(Field myField, String v1, List<String> strlist )
    {
        System.out.println("Field "+myField.getClass().getName() );
        Element one = myField.one();
        System.out.println(one);
        Element af = myField.parse( v1 );
        Element bf = myField.negative(af);
        System.out.println(myField.multiply(af,bf));
        Element cf = myField.inverse(af);    
        System.out.println(cf);
        System.out.println(myField.multiply(cf,af));
        System.out.println(myField.divide(af,bf));

        ArrayList<Element> list = new ArrayList<Element>();
        for( String s : strlist )
        {
            list.add( myField.parse(s));
        }
        System.out.println(myField.sum( list ));
        System.out.println(myField.product( list ));
    }
    
    public static void main( String[] args)
    {
        System.out.println("Start");
        Group myGroup = new FloatGroup();
        Field myField = new FloatField();
        
        groupTest( myGroup, args[0] );

        Group myIntGroup = new IntGroup();
        groupTest( myIntGroup, args[1] );


        System.out.println("Field");

        groupTest( myField, args[2] );

        fieldTest( myField, args[2], Arrays.asList("12.5","3.7","1.09") );

        System.out.println("ended normally");
    }
}