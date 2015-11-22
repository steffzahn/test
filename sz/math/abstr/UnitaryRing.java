package sz.math.abstr;

import sz.math.intf.IUnitaryRing;
import java.util.Iterator;

public abstract class UnitaryRing extends Ring implements IUnitaryRing
{
    protected abstract Element one_();
    public Element one()
    {
        Element result = one_();
        if( result==null )
        {
            throw new NullPointerException("one operation delivered null result");
        }
        if( result.getTheClass()!=this )
        {
            throw new RuntimeException("one operation delivers element outside of field");
        }
        return result;
    }

    // convenience methods
    public Element product( Iterable<Element> iterable )
    {
        if( iterable==null )
        {
            throw new RuntimeException("product operation missing mandatory parameters");
        }
        Element result = one();
        Iterator<Element> it = iterable.iterator();
        while( it.hasNext() )
        {
            result = this.multiply( result, it.next() ); 
        }
        return result;
    }
}