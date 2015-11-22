package sz.math.intf;
import sz.math.abstr.Element;

public interface IEuclideanRing extends IUnitaryRing
{
    public long norm(Element e);
    
    public interface Result
    {
        public Element quotient();

        public Element remainder();
    }
    
    public Result divideWithRemainder( Element a, Element b );
}