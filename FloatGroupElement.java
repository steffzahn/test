class FloatGroupElement extends GroupElement
{
    private double _value;
    
    private FloatGroupElement castIt( Object g )
    {
        if( g == null)
        {
            throw new NullPointerException("FloatGroup value was null");
        }
        if( g instanceof FloatGroupElement )
        {
            return (FloatGroupElement)g;
        }
        throw new RuntimeException("Not an element of FloatGroup");
    }
    
    protected FloatGroupElement( double v )
    {
        _value = v;
    }
    
    protected GroupElement plus_( GroupElement b )
    {
        return new FloatGroupElement( _value + castIt(b)._value );
    }
    
    protected GroupElement inverse_()
    {
        return new FloatGroupElement( - _value );
    }
    
    public boolean isZero()
    {
        return _value == 0.0;
    }
    
    public boolean equals( Object b)
    {
        FloatGroupElement bb = castIt(b);
        return _value == bb._value;
    }

    public String toString()
    {
        return Double.toString(_value);
    }
}