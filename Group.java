public interface Group
{
    public Element zero();
    
    public Element parse(String s);

    public Element plus( Element a, Element b );

    public boolean isZero(Element e);
    
    public Element negative(Element a);
}