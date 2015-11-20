public abstract class GroupElement
{
    protected abstract GroupElement plus_( GroupElement b );
    public GroupElement plus( GroupElement b )
    {
        GroupElement result = plus_(b);
        if( (b==null) || (b.getClass()!=getClass()) )
        {
            throw new RuntimeException("plus operation allows to process incompatible groups");
        }
        if( result.getClass()!=getClass() )
        {
            throw new RuntimeException("plus operation delivers element outside of group");
        }
        return result;
    }
    
    public abstract boolean isZero();
    
    protected abstract GroupElement inverse_();
    public GroupElement inverse()
    {
        GroupElement result = inverse_();
        if( result.getClass()!=getClass() )
        {
            throw new RuntimeException("inverse operation delivers element outside of group");
        }
        return result;
    }
}