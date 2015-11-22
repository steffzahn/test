package sz.math.impl;
import sz.math.abstr.Element;
import sz.math.abstr.Field;
import sz.math.intf.IField;

public class FloatField extends Field implements IField
{
    public FloatField()
    {}

    public FloatElement one_()
    {
        return new FloatElement( this, 1.0 );
    }

    protected Element multiply_( Element a,Element b )
    {
        FloatElement af = (FloatElement)a;
        FloatElement bf = (FloatElement)b;
        return new FloatElement( this, af._value * bf._value );
    }
    
    protected Element inverse_(Element a)
    {
        FloatElement af = (FloatElement)a;
        return new FloatElement( this, 1.0 / af._value );
    }

    protected Element parse_(String s)
    {
        return new FloatElement( this, Double.parseDouble(s) );
    }
    
    protected Element negative_(Element a)
    {
        FloatElement af = (FloatElement)a;
        return new FloatElement( this, - af._value );
    }
    
    public Element zero_()
    {
        return new FloatElement( this, 0.0 );
    }

    public boolean isZero_(Element e)
    {
        FloatElement ef = (FloatElement)e;
        return ef._value== 0.0;
    }

    public boolean isOne_(Element e)
    {
        FloatElement ef = (FloatElement)e;
        return ef._value== 1.0;
    }

    protected Element add_( Element a, Element b )
    {
        FloatElement af = (FloatElement)a;
        FloatElement bf = (FloatElement)b;
        return new FloatElement( this, af._value + bf._value );
    }
}