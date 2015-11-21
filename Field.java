public interface Field extends Group
{
    public Element one();

    public Element multiply( Element a, Element b );

    public Element inverse(Element a);
}