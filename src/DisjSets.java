// DisjSets class
//
// CONSTRUCTION: with int representing initial number of sets
//
// ******************PUBLIC OPERATIONS*********************
// void union( root1, root2 ) --> Merge two sets
// int find( x )              --> Return set containing x
// ******************ERRORS********************************
// No error checking is performed

/**
 * Disjoint set class, using union by rank and path compression.
 * Elements in the set are numbered starting at 0.
 * @author Mark Allen Weiss
 */
public class DisjSets
{
    /**
     * Construct the disjoint sets object.
     * @param numElements the initial number of disjoint sets.
     */
    public DisjSets( int numElements )
    {
        s = new int [ numElements ];
        for( int i = 0; i < s.length; i++ )
            s[ i ] = -1;
    }
    
    /**
    2 * Union two disjoint sets.
    3 * For simplicity, we assume root1 and root2 are distinct
    4 * and represent set names.
    5 * @param root1 the root of set 1.
    6 * @param root2 the root of set 2.
    7 */
    public void union( int root1, int root2 )
    {
    	s[ root2 ] = root1;
    }

    /**
    2 * Perform a find.
	3 * Error checks omitted again for simplicity.
	4 * @param x the element being searched for.
	5 * @return the set containing x.
     */
    public int find( int x )
    {
        if( s[ x ] < 0 )
            return x;
        else
            return find( s[ x ] );
    }

    private int [ ] s;

}