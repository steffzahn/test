#!/usr/bin/perl --

use strict;
use warnings;
use Digest::SHA qw(sha256);

my $DEBUG=0;

my $printable="abcdefghijkmnopqrstuvwxyz23456789!:?/&()-"; # size 41 (prime)
my $printable_len=length($printable);

die "Need 3 parameter" if( scalar(@ARGV)!=3 );

my $site=$ARGV[0];
my $pw=$ARGV[1];
my $l=$ARGV[2];

die "First parameter needs to be a non-empty string" if( !defined($site) || ($site eq "") );

print "Warning site $site might by misspelled\n"
    if($site !~ /^(google|facebook|instagram|pinterest|flickr|yahoo|vimeo|tumblr|cloud9|github|dropbox|twitter|wikipedia|apple|slashdot|skype|amazon|flipboard|wlan|bank|home|company)$/);

die "Second parameter needs to be a non-empty string" if( !defined($pw) || ($pw eq "") );

die "Third parameter is not a number" if( !defined($l) || ($l !~ /^\d+$/) );

sub hash256
{
    my $str= @_ ? shift @_ : undef;
    return unpack( "N",substr(sha256($str),0,4));
}

my $a = hash256( $site . $pw );
print "DEBUG a $a\n" if($DEBUG);

while($l>0)
{
    my $b = hash256( $a . $pw );
    print "DEBUG b $b\n" if($DEBUG);
    print substr($printable,$b % $printable_len,1);
    $a = $b;
    $l--;
}

print "\n";
exit 0;
