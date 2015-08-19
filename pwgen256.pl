#!/usr/bin/perl --

use strict;
use warnings;
use Digest::SHA qw(sha256);
# use Term::ReadPassword; # non-standard, apparently

my $DEBUG=0;

my $printable="abcdefghijkmnopqrstuvwxyz23456789!:?/&()-"; # size 41 (prime)
my $printable_len=length($printable);

my $site=$ARGV[0];
my $l=$ARGV[1];
my $pw=$ARGV[2];

sub prompt
{
    my $pr= @_ ? shift @_ : "Enter value:";
    my $line;
    while( !defined($line) || ($line eq "") )
    {
        print $pr;
        $line = <STDIN>;
        die "Read undefined value" if( !defined($line) );
        chomp $line;
        print "Need non-empty input\n" if( $line eq "" );
    }
    return $line;
}

if( !defined($site) )
{
    $site=prompt("Site:");
}
if( !defined($l) )
{
    $l=prompt("Length of generated password:");
}

if( !defined($pw) )
{
    system('stty', '-echo');  # Disable echoing
    $pw = prompt("Master password:");
    system('stty', 'echo');   # Turn it back on
    print "\n";
}

die "Need 3 parameter" if( !defined($site) || !defined($l) || !defined($pw) );

die "First parameter needs to be a non-empty string" if( !defined($site) || ($site eq "") );

print "Warning site $site might be misspelled\n"
    if($site !~ /^(google|facebook|instagram|pinterest|flickr|yahoo|vimeo|tumblr|cloud9|github|dropbox|twitter|wikipedia|apple|slashdot|skype|amazon|flipboard|wlan|bank|home|company)$/);

die "Second parameter is not a number" if( !defined($l) || ($l !~ /^\d+$/) );

die "Third parameter needs to be a non-empty string" if( !defined($pw) || ($pw eq "") );

sub blob2u32
{
    my $str= @_ ? shift @_ : undef;
    return unpack( "N",substr($str,0,4));
}

my $a = sha256( $site . $pw );
print "DEBUG a $a\n" if($DEBUG);

while($l>0)
{
    my $b = sha256( $a . $pw );
    print "DEBUG b $b\n" if($DEBUG);
    print substr($printable,blob2u32($b) % $printable_len,1);
    $a = $b;
    $l--;
}

print "\n";
exit 0;
