package sz.math.abstr;

import sz.math.intf.IRing;
import java.util.Iterator;

public abstract class Ring extends Group implements IRing
{
    protected abstract Element multiply_( Element a,Element b );
    public Element multiply( Element a, Element b )
    {
        if( (a==null) || (b==null) )
        {
            throw new RuntimeException("multiply operation missing mandatory parameters");
        }
        if( (a.getTheClass()!=this) || (b.getTheClass()!=this) )
        {
            throw new RuntimeException("multiply operation does not process incompatible rings");
        }
        Element result = multiply_(a,b);
        if( result==null )
        {
            throw new NullPointerException("multiply operation delivered null result");
        }
        if( result.getTheClass()!=this )
        {
            throw new RuntimeException("multiply operation delivers element outside of rings");
        }
        return result;
    }
}