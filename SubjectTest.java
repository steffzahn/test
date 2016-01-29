import java.security.acl.Group;
import javax.security.auth.Subject;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.Enumeration;
import java.util.Collections;


class SubjectTest
{
    private static class MyGroup implements Group
    {
        private HashSet<Principal> _members;
        private String _name;
        
        MyGroup( String name )
        {
            _name = name;
            _members = new HashSet<Principal>();
        }
        
        public boolean	addMember(Principal user)
        {
            if( _members.contains(user) )
            {
                return false;
            }
            _members.add( user );
            return true;
        }

        public boolean	isMember(Principal member)
        {
            return _members.contains(member);
        }

        public Enumeration<Principal>	members()
        {
            return Collections.enumeration( _members );
        }

        public boolean	removeMember(Principal user)
        {
            if( !_members.contains(user) )
            {
                return false;
            }
            _members.remove( user );
            return true;
        }
        
        public String	getName()
        {
            return _name;
        }
    }

    private static class MyPrincipal implements Principal
    {
        private String _name;
        
        MyPrincipal(String name)
        {
            _name = name;
        }
        
        public String getName()
        {
            return _name;
        }
    }
    
    private void test()
    {
        Subject subject = new Subject();
        Set<Principal> rawListOfPrincipals =  subject.getPrincipals();
        MyGroup group = new MyGroup("Roles");
        if( group instanceof Group )
        {
            System.out.println("is Group");
        }
        rawListOfPrincipals.add( group );
        MyPrincipal id = new MyPrincipal("itsMe");
        rawListOfPrincipals.add( id );
        Set<Group> principalListFilteredByGroupClass =  subject.getPrincipals(Group.class);
        System.out.println( principalListFilteredByGroupClass.size() + "," +  principalListFilteredByGroupClass.toString() );
        Set<MyPrincipal> principalListFilteredByMyPrincipalClass =  subject.getPrincipals(MyPrincipal.class);
        System.out.println( principalListFilteredByMyPrincipalClass.size() + "," +  principalListFilteredByMyPrincipalClass.toString() );
        Set<Principal> readRawListOfPrincipalsAgain =  subject.getPrincipals();
        System.out.println( readRawListOfPrincipalsAgain.size() + "," +  readRawListOfPrincipalsAgain.toString() );
    }
    
    public static void main(String[] args)
    {
        SubjectTest st = new SubjectTest();
        st.test();
    }
}