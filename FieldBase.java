public abstract class FieldBase extends GroupBase implements Field
{
    protected abstract Element one_();
    public Element one()
    {
        Element result = one_();
        if( result.getTheClass()!=zero().getTheClass() )
        {
            throw new RuntimeException("one and zero are not in the same field");
        }
        return result;
    }

    protected abstract Element multiply_( Element a,Element b );
    public Element multiply( Element a, Element b )
    {
        Element result = multiply_(a,b);
        if( (a==null) || (b==null) || (a.getTheClass()!=b.getTheClass()) )
        {
            throw new RuntimeException("multiply operation allows to process incompatible fields");
        }
        if( result==null )
        {
            throw new NullPointerException("multiply operation delivered null result");
        }
        if( result.getTheClass()!=a.getTheClass() )
        {
            throw new RuntimeException("multiply operation delivers element outside of field");
        }
        return result;
    }

    protected abstract Element inverse_(Element a);
    public Element inverse(Element a)
    {
        Element result = inverse_(a);
        if( result==null )
        {
            throw new NullPointerException("inverse operation delivered null result");
        }
        if( (a==null) || (result.getTheClass()!=a.getTheClass()) )
        {
            throw new RuntimeException("inverse operation delivers element outside of field");
        }
        if( this.isZero(a) )
        {
            throw new RuntimeException("inverse operation accepts zero");
        }
        return result;
    }
}