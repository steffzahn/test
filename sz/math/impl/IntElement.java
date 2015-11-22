package sz.math.impl;
import sz.math.abstr.Element;

public class IntElement extends Element
{
    protected long _value;
    private boolean _hashComputed;
    private int _hashValue;
    
    private IntElement castIt( Object g )
    {
        if( g == null)
        {
            throw new NullPointerException("IntElement value was null");
        }
        if( g instanceof IntElement )
        {
            return (IntElement)g;
        }
        throw new RuntimeException("Not an element of IntGroup");
    }

    protected IntElement( Object cl, long v )
    {
        super( cl );
        _value = v;
        _hashComputed = false;
    }

    public boolean equals( Object b)
    {
        IntElement bb = castIt(b);
        return _value == bb._value;
    }

    public int hashCode()
    {
        if( !_hashComputed )
        {
            _hashValue = new Long(_value).hashCode();
            _hashComputed = true;
        }
        return _hashValue;
    }
    
    public String toString()
    {
        return Long.toString(_value);
    }
}