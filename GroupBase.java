public abstract class GroupBase implements Group
{
    public abstract Element zero();
    
    protected abstract Element parse_(String s);
    public Element parse(String s)
    {
        Element result= parse_(s);
        if( result==null )
        {
            throw new NullPointerException("parse operation delivered null result");
        }
        if( (s==null) || (result.getTheClass()!=zero().getTheClass()) )
        {
            throw new RuntimeException("parse operation delivers element outside of group");
        }
        return result;
    }

    protected abstract Element plus_( Element a, Element b );
    public Element plus( Element a, Element b )
    {
        Element result = plus_(a,b);
        if( result==null )
        {
            throw new NullPointerException("plus operation delivered null result");
        }
        if( (a==null) || (b==null) || (a.getTheClass()!=b.getTheClass()) )
        {
            throw new RuntimeException("plus operation allows to process incompatible groups");
        }
        if( result.getTheClass()!=a.getTheClass() )
        {
            throw new RuntimeException("plus operation delivers element outside of group");
        }
        return result;
    }
    
    public abstract boolean isZero(Element e);
    
    protected abstract Element negative_(Element a);
    public Element negative(Element a)
    {
        Element result = negative_(a);
        if( (a==null) || (result.getTheClass()!=a.getTheClass()) )
        {
            throw new RuntimeException("negative operation delivers element outside of group");
        }
        return result;
    }
}