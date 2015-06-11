package com.myhome.jdk5.collection.comparable;
public class Foo implements Comparable {
  private int i;
  private String s;
 
  public Foo( int i, String s ) {
    this.i = i;
    this.s = s;
  }
 
  public boolean equals( Object o ) {
    if ( this == o ) {
      return true;
    }
 
    if ( o == null || !getClass().equals( o.getClass() ) ) {
      return false;
    }
 
    Foo other = (Foo) o;
    return ( i == other.i ) && ( ( s == null ) ? ( other.s == null ) : s.equals( other.s ) );
  }
 
  public int compareTo( Object o ) {
    Foo other = (Foo) o;
 
    int intComparison = i - other.i;
    if ( intComparison != 0 ) {
      return intComparison;
    }
    else {
      int stringComparison;
 
      if ( s == null ) {
        stringComparison = ( other.s == null ) ? 0 : -1;
      }
      else {
        stringComparison = ( other.s == null ) ? 1 : s.compareTo( other.s );
      }
 
      return stringComparison;
    }
  }
}
