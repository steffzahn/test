//============================================================================
// Name        : mt.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <map>

using namespace std;

class Key
{
	int a,b;
public:
	Key() : a(0), b(0)
	{}
	Key(int av, int bv) : a(av), b(bv)
	{}
	bool operator<( const Key& p2 ) const
	{
		return ( ( a<p2.a ) || ( (a==p2.a) && (b<p2.b) ));
	}
	bool operator>( const Key& p2 ) const
	{
		return ! (*this < p2 );
	}
	bool operator==( const Key& p2 ) const
	{
		return ( ( a==p2.a ) && ( b==p2.b ));
	}
	bool operator<=( const Key& p2 ) const
	{
		return (*this < p2 ) || (*this == p2);
	}
	bool operator!=( const Key& p2 ) const
	{
		return !(*this == p2);
	}
	bool operator>=( const Key& p2 ) const
	{
		return (*this > p2 ) || (*this == p2);
	}

	friend std::ostream& operator<< ( std::ostream& os, const Key& k );
};

inline std::ostream& operator<< ( std::ostream& os, const Key& k )
{
	return os<< k.a << "," << k.b;
}

int main() {
	map<Key,int> myMap;
	myMap[Key(8,6)]=42;
	myMap[Key(1,5)]=77;
	myMap[Key(8,9)]=23;
	cout << "!!!Hello World!!!" << endl; // prints !!!Hello World!!!
	cout << myMap.empty() << endl;
	for( std::pair<Key, int> v : myMap )
	{
		cout << v.first << endl;
		cout << v.second << endl;
	}
	return 0;
}
