import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import sz.math.abstr.Group;
import sz.math.abstr.Ring;
import sz.math.abstr.Field;
import sz.math.abstr.Element;

import sz.math.impl.IntGroup;
import sz.math.impl.IntRing;
import sz.math.impl.FloatGroup;
import sz.math.impl.FloatRing;
import sz.math.impl.FloatField;


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

        System.out.println(myGroup.sum( myGroup.parseList(strlist) ));
    }
    
    private static void ringTest(Ring myRing, String v1, List<String> strlist )
    {
        groupTest( myRing, v1, strlist );
        System.out.println("Ring "+myRing.getClass().getName() );
        Element af = myRing.parse( v1 );
        Element bf = myRing.negative(af);
        System.out.println(myRing.multiply(af,bf));
    }

    private static void fieldTest(Field myField, String v1, List<String> strlist )
    {
        ringTest( myField, v1, strlist );
        System.out.println("Field "+myField.getClass().getName() );
        Element one = myField.one();
        System.out.println(one);
        Element af = myField.parse( v1 );
        System.out.println(myField.multiply(af,one));
        Element cf = myField.inverse(af);    
        System.out.println(cf);
        System.out.println(myField.multiply(cf,af));
        System.out.println(myField.divide(af,cf));

        System.out.println(myField.product( myField.parseList( strlist ) ));
    }
    
    public static void main( String[] args)
    {
        System.out.println("Start");
        Group myGroup = new FloatGroup();
        Ring myRing = new FloatRing();
        Field myField = new FloatField();
        
        List<String> floatList = Arrays.asList("12.5","3.7","1.09");
        List<String> intList = Arrays.asList("12","7","1");
        
        String v0 = (args.length>0) ? args[0] : "12.3";
        String v1 = (args.length>1) ? args[1] : "5";
        String v2 = (args.length>2) ? args[2] : "2.2";
        
        groupTest( myGroup, v0, floatList );

        Group myIntGroup = new IntGroup();
        groupTest( myIntGroup, v1, intList );

        Ring myIntRing = new IntRing();
        ringTest( myIntRing, v1, intList );

        System.out.println("Ring");
        ringTest( myRing, v0, floatList );

        System.out.println("Field");
        fieldTest( myField, v2, floatList );

        System.out.println("ended normally");
    }
}