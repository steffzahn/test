#!/usr/bin/perl --

# generate site specific passwords from one (or more) master password(s)
# Steffen Zahn steffzahn@gmail.com

use strict;
use warnings;
use Digest::SHA qw(sha256);
# use Term::ReadPassword; # non-standard, apparently

my $DEBUG=0;

my $printable="abcdefghijkmnopqrstuvwxyz23456789!:?/&()-"; # size 41 (prime)
my $printable_len=length($printable);

my $site=$ARGV[0];
my $length=$ARGV[1];
my $pw=$ARGV[2];

my %site2defaultPWLength =
    (
        "google" => 16,
        "facebook" => 16,
        "instagram" => 16,
        "pinterest" => 16,
        "flickr" => 16,
        "yahoo" => 16,
        "vimeo" => 16,
        "tumblr" => 16,
        "cloud9" => 16,
        "github" => 16,
        "dropbox" => 16,
        "twitter" => 16,
        "wikipedia" => 16,
        "apple" => 16,
        "slashdot" => 16,
        "skype" => 16,
        "amazon" => 16,
        "flipboard" => 16,
        "wlan" => 16,
        "bank" => 16,
        "home" => 16,
        "company" => 16
    );

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

die "First parameter needs to be a non-empty string" if( !defined($site) || ($site eq "") );

print "Warning site $site might be misspelled\n"
    if( !defined( $site2defaultPWLength{$site} ) );

if( !defined($length) )
{
    if( defined( $site2defaultPWLength{$site} ) )
    {
        $length = $site2defaultPWLength{$site};
        print "Using default password length of $length defined for site \"$site\"\n";
    } else {
        $length=prompt("Length of generated password:");
    }
}

if( !defined($pw) )
{
    system('stty', '-echo');  # Disable echoing
    $pw = prompt("Master password:");
    system('stty', 'echo');   # Turn it back on
    print "\n";
}

die "Need 3 parameter" if( !defined($site) || !defined($length) || !defined($pw) );

die "Second parameter is not a number" if( !defined($length) || ($length !~ /^\d+$/) );

die "Third parameter needs to be a non-empty string" if( !defined($pw) || ($pw eq "") );

sub blob2u32
{
    my $str= @_ ? shift @_ : undef;
    return unpack( "N",substr($str,0,4));
}

my $a = sha256( $site . $pw );
print "DEBUG a $a\n" if($DEBUG);

while($length>0)
{
    my $b = sha256( $a . $pw );
    print "DEBUG b $b\n" if($DEBUG);
    print substr($printable,blob2u32($b) % $printable_len,1);
    $a = $b;
    $length--;
}

print "\n";
exit 0;
