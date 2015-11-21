class FloatGroup extends GroupBase implements Group
{
    public FloatGroup()
    {}

    private FloatElement castIt( Element g )
    {
        if( g == null)
        {
            throw new NullPointerException("Element value was null");
        }
        if( g instanceof FloatElement )
        {
            return (FloatElement)g;
        }
        throw new RuntimeException("Not en element of FloatGroup");
    }
    
    protected Element parse_(String s)
    {
        return new FloatElement( this, Double.parseDouble(s) );
    }
    
    protected Element negative_(Element a)
    {
        FloatElement af = castIt( a );
        return new FloatElement( this, - af._value );
    }
    
    public Element zero()
    {
        return new FloatElement( this, 0.0 );
    }

    public boolean isZero(Element e)
    {
        FloatElement ef = castIt( e );
        return ef._value== 0.0;
    }

    protected Element plus_( Element a, Element b )
    {
        FloatElement af = castIt( a );
        FloatElement bf = castIt( b );
        return new FloatElement( this, af._value + bf._value );
    }
}