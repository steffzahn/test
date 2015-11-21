public interface IGroup
{
    public Element zero();
    
    public Element parse(String s);

    public Element add( Element a, Element b );

    public boolean isZero(Element e);
    
    public Element negative(Element a);
}