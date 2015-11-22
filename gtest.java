import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import sz.math.abstr.Group;
import sz.math.abstr.Ring;
import sz.math.abstr.UnitaryRing;
import sz.math.abstr.Field;
import sz.math.abstr.Element;

import sz.math.impl.IntGroup;
import sz.math.impl.IntRing;
import sz.math.impl.IntUnitaryRing;
import sz.math.impl.FloatGroup;
import sz.math.impl.FloatRing;
import sz.math.impl.FloatUnitaryRing;
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
        
        System.out.println( myGroup.parseList(strlist) );
    }
    
    private static void ringTest(Ring myRing, String v1, List<String> strlist )
    {
        groupTest( myRing, v1, strlist );
        System.out.println("Ring "+myRing.getClass().getName() );
        Element af = myRing.parse( v1 );
        System.out.println(af);
        Element bf = myRing.negative(af);
        System.out.println(bf);
        System.out.println(myRing.multiply(af,bf));
    }

    private static void unitaryRingTest(UnitaryRing myUnitaryRing, String v1, List<String> strlist )
    {
        ringTest( myUnitaryRing, v1, strlist );
        System.out.println("UnitaryRing "+myUnitaryRing.getClass().getName() );
        Element one = myUnitaryRing.one();
        System.out.println(one);
        Element af = myUnitaryRing.parse( v1 );
        System.out.println(myUnitaryRing.multiply(af,one));

        System.out.println(myUnitaryRing.product( myUnitaryRing.parseList( strlist ) ));
    }

    private static void fieldTest(Field myField, String v1, List<String> strlist )
    {
        unitaryRingTest( myField, v1, strlist );
        System.out.println("Field "+myField.getClass().getName() );
        Element af = myField.parse( v1 );
        Element cf = myField.inverse(af);    
        System.out.println(cf);
        System.out.println(myField.divide(af,cf));
    }
    
    public static void main( String[] args)
    {
        System.out.println("Start");
        
        List<String> floatList = Arrays.asList("12.5","3.7","1.09");
        List<String> intList = Arrays.asList("12","7","1");
        
        String v0 = (args.length>0) ? args[0] : "12.3";
        String v1 = (args.length>1) ? args[1] : "5";
        String v2 = (args.length>2) ? args[2] : "2.2";
        
        Group myGroup = new FloatGroup();
        groupTest( myGroup, v0, floatList );

        Group myIntGroup = new IntGroup();
        groupTest( myIntGroup, v1, intList );

        System.out.println("Ring");
        Ring myIntRing = new IntRing();
        ringTest( myIntRing, v1, intList );

        Ring myRing = new FloatRing();
        ringTest( myRing, v0, floatList );

        System.out.println("UnitaryRing");
        UnitaryRing myUnitaryRing = new FloatUnitaryRing();
        unitaryRingTest( myUnitaryRing, v0, floatList );

        UnitaryRing myIntUnitaryRing = new IntUnitaryRing();
        unitaryRingTest( myIntUnitaryRing, v1, intList );

        System.out.println("Field");
        Field myField = new FloatField();
        fieldTest( myField, v2, floatList );

        System.out.println("ended normally");
    }
}