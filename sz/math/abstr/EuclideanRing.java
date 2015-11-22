package sz.math.abstr;

import sz.math.intf.IEuclideanRing;
import java.util.Iterator;

public abstract class EuclideanRing extends UnitaryRing implements IEuclideanRing
{
    protected abstract long norm_(Element a);
    public long norm(Element a)
    {
        if( (a==null) || (a.getTheClass()!=this) )
        {
            throw new RuntimeException("norm operation does not process element outside of euclidean ring");
        }
        return norm_(a);
    }

    protected abstract IEuclideanRing.Result divideWithRemainder_( Element a, Element b );
    public IEuclideanRing.Result divideWithRemainder( Element a, Element b )
    {
        if( (a==null) || (b==null) )
        {
            throw new RuntimeException("divideWithRemainder operation missing mandatory parameters");
        }
        if( (a.getTheClass()!=this) || (b.getTheClass()!=this) )
        {
            throw new RuntimeException("divideWithRemainder operation does not process incompatible euclidean ring");
        }
        if( this.isZero(b) )
        {
            throw new RuntimeException("divideWithRemainder operation does not accept zero dividend");
        }
        IEuclideanRing.Result result = divideWithRemainder_(a,b);
        if( result==null )
        {
            throw new NullPointerException("divideWithRemainder operation delivered null result");
        }
        Element q=result.quotient();
        Element r=result.remainder();
        if( (q==null) || (r==null) )
        {
            throw new NullPointerException("divideWithRemainder operation delivered null result");
        }
        if( (q.getTheClass()!=this) || (r.getTheClass()!=this) )
        {
            throw new RuntimeException("divideWithRemainder operation delivers element outside of euclidean ring");
        }
        return result;
    }

    // convenience methods
    public Element euclid( Element a, Element b )
    {
        if( (a==null) || (b==null) )
        {
            throw new RuntimeException("divideWithRemainder operation missing mandatory parameters");
        }
        if( (a.getTheClass()!=this) || (b.getTheClass()!=this) )
        {
            throw new RuntimeException("divideWithRemainder operation does not process incompatible euclidean ring");
        }
        for(;;)
        {
            if( this.isZero( b ) )
            {
                return a;
            }
            if( this.isZero( a ) )
            {
                return b;
            }
            if( this.norm(a) >= this.norm(b) )
            {
                Element q = this.divideWithRemainder( a, b ).remainder();
                a = b;
                b = q;
            } else {
                Element q = this.divideWithRemainder( b, a ).remainder();
                b = a;
                a = q;
            }
        }
    }
}