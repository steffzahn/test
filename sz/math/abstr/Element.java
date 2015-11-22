package sz.math.abstr;

public abstract class Element
{
    private Object _parent;
    
    public Object getTheClass()
    {
        return _parent;
    }
    
    protected Element(Object p)
    {
        if( p==null )
        {
            throw new NullPointerException( "mandatory parameter missing");
        }
        _parent = p;
    }
}