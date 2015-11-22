package sz.math.impl;
import sz.math.abstr.Element;

public class FloatElement extends Element
{
    protected double _value;
    private boolean _hashComputed;
    private int _hashValue;
    
    private FloatElement castIt( Object g )
    {
        if( g == null)
        {
            throw new NullPointerException("FloatElement value was null");
        }
        if( g instanceof FloatElement )
        {
            return (FloatElement)g;
        }
        throw new RuntimeException("Not an element of FloatGroup");
    }

    protected FloatElement( Object cl, double v )
    {
        super( cl );
        _value = v;
        _hashComputed = false;
    }

    public boolean equals( Object b)
    {
        FloatElement bb = castIt(b);
        return _value == bb._value;
    }

    public int hashCode()
    {
        if( !_hashComputed )
        {
            _hashValue = new Double(_value).hashCode();
            _hashComputed = true;
        }
        return _hashValue;
    }
    
    public String toString()
    {
        return Double.toString(_value);
    }
}