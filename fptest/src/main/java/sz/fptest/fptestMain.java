package sz.fptest;

import java.util.ArrayList;
import java.util.List;

public class fptestMain {
    private static List<String> makeList()
    {
        List<String> result = new ArrayList<>();
        for( int i=0; i<10000; i++)
        {
            result.add( Integer.toString(i) );
        }
        return result;
    }

    private static String tfuntional(List<String> list)
    {
        String result = list
                .stream()
                .map(name -> name + " ")
                .reduce("", (acc, name) -> acc+name);
        return result;
    }

    private static String tfuntionalPerverted(List<String> list)
    {
        StringBuilder sb = new StringBuilder(20000);
        String result = list
                .stream()
                .reduce("", (acc, name) -> { sb.append(name).append(' '); return ""; } );
        return sb.toString();
    }

    private static String tnormal(List<String> list)
    {
        String result="";
        for( String s : list)
        {
            result += s+' ';
        }
        return result;
    }

    private static String tnormalPrealloc(List<String> list)
    {
        StringBuilder sb = new StringBuilder(20000);
        for( String s : list)
        {
            sb.append(s).append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        List<String> list = makeList();
        for( int round=1;round<=100;round++) {
            long t1 = System.currentTimeMillis();
            String sf = tfuntional(list);
            long t2 = System.currentTimeMillis();
            String sn = tnormal(list);
            long t3 = System.currentTimeMillis();
            String snp = tnormalPrealloc(list);
            long t4 = System.currentTimeMillis();
            String sfp = tfuntionalPerverted(list);
            long t5 = System.currentTimeMillis();
            System.out.println("tfunctional:" + (t2 - t1) + ", tnormal:" + (t3 - t2) + ", tnormalPrealloc:" + (t4 - t3)+ ", tfunctionalPerverted:" + (t5 - t4));
            if (!sn.equals(sf)) {
                System.out.println("sn != sf");
            }
            if (!snp.equals(sf)) {
                System.out.println("snp != sf");
            }
            if (!sfp.equals(sf)) {
                System.out.println("sfp != sf");
            }
        }
    }
}
