package sz.math.intf;
import sz.math.abstr.Element;

public interface IField extends IRing
{
    public Element one();

    public Element inverse(Element a);
}