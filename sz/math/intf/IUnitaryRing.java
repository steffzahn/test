package sz.math.intf;
import sz.math.abstr.Element;

public interface IUnitaryRing extends IRing
{
    public Element one();

    public boolean isOne(Element e);
}