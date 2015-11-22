package sz.math.intf;
import sz.math.abstr.Element;

public interface IField extends IUnitaryRing
{
    public Element inverse(Element a);
}