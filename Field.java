import java.util.Iterator;

public abstract class Field extends Ring implements IField
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

    protected abstract Element inverse_(Element a);
    public Element inverse(Element a)
    {
        if( (a==null) || (a.getTheClass()!=this) )
        {
            throw new RuntimeException("inverse operation does not process element outside of field");
        }
        if( this.isZero(a) )
        {
            throw new RuntimeException("inverse operation does not accept zero");
        }
        Element result = inverse_(a);
        if( result==null )
        {
            throw new NullPointerException("inverse operation delivered null result");
        }
        if( result.getTheClass()!=this )
        {
            throw new RuntimeException("inverse operation delivers element outside of field");
        }
        return result;
    }

    // convenience methods
    public Element divide( Element a, Element b )
    {
        return this.multiply(a,this.inverse(b));
    }

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