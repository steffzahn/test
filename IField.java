public interface IField extends IGroup
{
    public Element one();

    public Element multiply( Element a, Element b );

    public Element inverse(Element a);
}