public class IntRing extends Ring implements IRing
{
    protected IntRing()
    {}

    protected Element multiply_( Element a,Element b )
    {
        IntElement af = (IntElement)a;
        IntElement bf = (IntElement)b;
        return new IntElement( this, af._value * bf._value );
    }
    
    protected Element parse_(String s)
    {
        return new IntElement( this, Long.parseLong(s) );
    }
    
    protected Element negative_(Element a)
    {
        IntElement af = (IntElement)a;
        return new IntElement( this, - af._value );
    }
    
    public Element zero_()
    {
        return new IntElement( this, 0L );
    }

    public boolean isZero_(Element e)
    {
        IntElement ef = (IntElement)e;
        return ef._value== 0L;
    }

    protected Element add_( Element a, Element b )
    {
        IntElement af = (IntElement)a;
        IntElement bf = (IntElement)b;
        return new IntElement( this, af._value + bf._value );
    }
}