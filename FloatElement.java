class FloatElement extends Element
{
    protected double _value;
    
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
    }

    public boolean equals( Object b)
    {
        FloatElement bb = castIt(b);
        return _value == bb._value;
    }

    public String toString()
    {
        return Double.toString(_value);
    }
}