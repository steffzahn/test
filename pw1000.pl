#!/usr/bin/perl --

use strict;
use warnings;

my $needed_words = 5;

sub myrand($)
{
    my $modu = shift @_;
    die "Missing parameter" if( !defined($modu) );
    open( RND, "/dev/random") || die "Cannot open /dev/random";
    my $buf ="";
    sysread( RND, $buf, 4 ); 
    close( RND);
    die "Failed to read 4 bytes from /dev/random" if( length($buf)!=4);
    my $v = unpack( "N", $buf);
    die "Failed to unpack number" if( !defined($v) || ($v<0) );
    return ($v % $modu);
}

if( scalar(@ARGV)>0 )
{
    $needed_words = $ARGV[0]+0;
}

my $WORD_FILE = "top1000de.txt";

open( INFILE, "$WORD_FILE" ) || die "Cannot open $WORD_FILE";

my @words = <INFILE>;

close( INFILE );

die "File possibly corrupted ( expet 1000 words)" if( scalar(@words) != 1000 );

while( $needed_words>0 )
{
    my $index = int(myrand(1000));
    my $w = $words[$index];
    chomp $w;
    print "$w ";
    $needed_words--;
}
print "\n";
