import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class gtest
{
    private static void groupTest(Group myGroup, String v1, List<String> strlist )
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

        ArrayList<Element> list = new ArrayList<Element>();
        for( String s : strlist )
        {
            list.add( myGroup.parse(s));
        }
        System.out.println(myGroup.sum( list ));
    }
    
    private static void ringTest(Ring myRing, String v1, List<String> strlist )
    {
        System.out.println("Ring "+myRing.getClass().getName() );
        Element af = myRing.parse( v1 );
        Element bf = myRing.negative(af);
        System.out.println(myRing.multiply(af,bf));

        ArrayList<Element> list = new ArrayList<Element>();
        for( String s : strlist )
        {
            list.add( myRing.parse(s));
        }
        System.out.println(myRing.sum( list ));
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
        Ring myRing = new FloatRing();
        Field myField = new FloatField();
        
        List<String> floatList = Arrays.asList("12.5","3.7","1.09");
        List<String> intList = Arrays.asList("12","7","1");
        
        groupTest( myGroup, args[0], floatList );

        Group myIntGroup = new IntGroup();
        groupTest( myIntGroup, args[1], intList );

        Ring myIntRing = new IntRing();
        ringTest( myIntRing, args[1], intList );

        System.out.println("Ring");
        groupTest( myRing, args[2], floatList );
        ringTest( myRing, args[0], floatList );

        System.out.println("Field");

        groupTest( myField, args[2], floatList );

        fieldTest( myField, args[2], floatList );

        System.out.println("ended normally");
    }
}