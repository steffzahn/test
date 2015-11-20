public abstract class Group
{
    public abstract GroupElement zero();
    
    protected abstract GroupElement parse_(String s);
    public GroupElement parse(String s)
    {
        GroupElement result= parse_(s);
        if( (s==null) || (result.getClass()!=zero().getClass()) )
        {
            throw new RuntimeException("parse operation delivers element outside of group");
        }
        return result;
    }
}