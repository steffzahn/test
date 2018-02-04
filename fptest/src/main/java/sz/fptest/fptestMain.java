package sz.fptest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class fptestMain {

    private String testO(Integer i, Function<Integer,String> t)
    {
        System.out.println( t.getClass().getName() );
        return t.apply(i);
    }

    private List<String> makeList()
    {
        List<String> result = new ArrayList<>();
        for( int i=0; i<10000; i++)
        {
            result.add( Integer.toString(i) );
        }
        return result;
    }

    private String tfuntional(List<String> list)
    {
        return list
                .stream()
                .collect(Collectors.joining(" ","", " "));
    }

    private String tfuntionalPerverted(List<String> list)
    {
        StringBuilder sb = new StringBuilder(20000);
        String result = list
                .stream()
                .reduce("", (acc, name) -> { sb.append(name).append(' '); return ""; } );
        return sb.toString();
    }

    private String tfuntionalWasteLotsOfObjects(List<String> list) {
        String result = list
                .stream()
                .map(name -> name + " ")
                .reduce("", (acc, name) -> acc + name);
        return result;
    }

    private String tnormalWasteLotsOfObjects(List<String> list)
    {
        String result="";
        for( String s : list)
        {
            result += s+' ';
        }
        return result;
    }

    private String tnormalPrealloc(List<String> list)
    {
        StringBuilder sb = new StringBuilder(20000);
        for( String s : list)
        {
            sb.append(s).append(' ');
        }
        return sb.toString();
    }

    private void run()
    {
        System.out.println( testO( 5, i -> { return i.toString(); } ));
        List<String> list = makeList();
        for( int round=1;round<=25;round++) {
            long t1 = System.currentTimeMillis();
            String sf = tfuntional(list);
            long t2 = System.currentTimeMillis();
            String sn = tnormalWasteLotsOfObjects(list);
            long t3 = System.currentTimeMillis();
            String snp = tnormalPrealloc(list);
            long t4 = System.currentTimeMillis();
            String sfp = tfuntionalPerverted(list);
            long t5 = System.currentTimeMillis();
            String sfwlo = tfuntionalWasteLotsOfObjects(list);
            long t6 = System.currentTimeMillis();
            System.out.println("tfunctional:" + (t2 - t1) + ", tnormalWasteLotsOfObjects:" + (t3 - t2) + ", tnormalPrealloc:" + (t4 - t3)
                    + ", tfunctionalPerverted:" + (t5 - t4) + ", tfuntionalWasteLotsOfObjects:" + (t6 - t5));
            if (!sn.equals(sf)) {
                System.out.println("sn != sf");
            }
            if (!snp.equals(sf)) {
                System.out.println("snp != sf");
            }
            if (!sfp.equals(sf)) {
                System.out.println("sfp != sf");
            }
            if (!sfwlo.equals(sf)) {
                System.out.println("sfwlo != sf");
            }
        }
    }

    public static void main(String[] args)
    {
        new fptestMain().run();
    }
}
