package sz.math.abstr;

import sz.math.intf.IGroup;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public abstract class Group implements IGroup
{
    protected abstract Element zero_();
    public Element zero()
    {
        Element result = zero_();
        if( result==null )
        {
            throw new NullPointerException("zero operation delivered null result");
        }
        if( result.getTheClass()!=this )
        {
            throw new RuntimeException("zero operation delivers element outside of group");
        }
        return result;
    }
    
    protected abstract Element parse_(String s);
    public Element parse(String s)
    {
        if( s==null )
        {
            throw new RuntimeException("parse operation missing mandatory parameter");
        }
        Element result= parse_(s);
        if( result==null )
        {
            throw new NullPointerException("parse operation delivered null result");
        }
        if( result.getTheClass()!=this )
        {
            throw new RuntimeException("parse operation delivers element outside of group");
        }
        return result;
    }

    protected abstract Element add_( Element a, Element b );
    public Element add( Element a, Element b )
    {
        if( (a==null) || (b==null) )
        {
            throw new RuntimeException("add operation missing mandatory parameters");
        }
        if( (a.getTheClass()!=this) || (b.getTheClass()!=this) )
        {
            throw new RuntimeException("add operation does not process incompatible groups");
        }
        Element result = add_(a,b);
        if( result==null )
        {
            throw new NullPointerException("add operation delivered null result");
        }
        if( result.getTheClass()!=this )
        {
            throw new RuntimeException("add operation delivers element outside of group");
        }
        return result;
    }
    
    protected abstract boolean isZero_(Element e);
    public boolean isZero(Element e)
    {
        if( e==null )
        {
            throw new RuntimeException("isZero operation missing mandatory parameters");
        }
        if( this!=e.getTheClass()  )
        {
            throw new RuntimeException("isZero operation does not accept parameters from a different group");
        }
        return isZero_(e);
    }
    
    protected abstract Element negative_(Element a);
    public Element negative(Element a)
    {
        if( a==null )
        {
            throw new RuntimeException("negative operation missing mandatory parameters");
        }
        if( this!=a.getTheClass()  )
        {
            throw new RuntimeException("negative operation does not accept parameters from a different group");
        }
        Element result = negative_(a);
        if( result==null )
        {
            throw new NullPointerException("negative operation delivered null result");
        }
        if( result.getTheClass()!=this )
        {
            throw new RuntimeException("negative operation delivers element outside of group");
        }
        return result;
    }
    
    // convenience methods
    public Element minus( Element a, Element b )
    {
        return this.add(a,this.negative(b));
    }

    public Element sum( Iterable<Element> iterable )
    {
        if( iterable==null )
        {
            throw new RuntimeException("sum operation missing mandatory parameters");
        }
        Element result = zero();
        Iterator<Element> it = iterable.iterator();
        while( it.hasNext() )
        {
            result = this.add( result, it.next() ); 
        }
        return result;
    }

    public List<Element> parseList( Iterable<String> iterable )
    {
        if( iterable==null )
        {
            throw new RuntimeException("parseList operation missing mandatory parameters");
        }
        ArrayList<Element> result = new ArrayList<Element>();
        Iterator<String> it = iterable.iterator();
        while( it.hasNext() )
        {
            result.add( this.parse( it.next() ) );
        }
        return result;
    }
}