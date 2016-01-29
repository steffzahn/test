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
    
    private void test()
    {
        Subject sub = new Subject();
        Set<Principal> prmg =  sub.getPrincipals();
        //Set prl =  sub.getPrincipals(MyGroup.class);
        MyGroup gr = new MyGroup("Roles");
        if( gr instanceof Group )
        {
            System.out.println("is Group");
        }
        prmg.add( gr );
        Set<Group> prl =  sub.getPrincipals(Group.class);
        System.out.println( prl.size() );
        System.out.println( prl.toString() );
    }
    
    public static void main(String[] args)
    {
        SubjectTest st = new SubjectTest();
        st.test();
    }
}