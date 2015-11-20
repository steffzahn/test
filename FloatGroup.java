class FloatGroup extends Group
{
    private FloatGroup()
    {}
    
    private static final FloatGroup _instance = new FloatGroup();
    
    public static FloatGroup instance()
    {
        return _instance;
    }
    
    private FloatGroup castIt( Group g )
    {
        if( g == null)
        {
            throw new NullPointerException("FloatGroup value was null");
        }
        if( g instanceof FloatGroup )
        {
            return (FloatGroup)g;
        }
        throw new RuntimeException("Not en element of FloatGroup");
    }
    
    public GroupElement zero()
    {
        return new FloatGroupElement( 0.0 );
    }
    
    protected GroupElement parse_(String s)
    {
        return new FloatGroupElement( Double.parseDouble(s) );
    }
    
}