package com.myhome.jdk5.genericite;

/**
 * Utility class to factorize warnings in casting process.
 * @author vdubus
 */
public final class CastUtils {

    /**
     * Private constructor to avoid instantiation of this utility class.
     */
    private CastUtils() {
        super();
    }

    /**
     * Cast an object into the specified type.<br>
     * This allow us to factorize the warning in case of generic or raw type cast. Useful when using api which don't use generic.<br>
     * Example:<br>
     * {@code
     * List<String> myList = CastUtils.cast(method.getRawList());
     * myObject.useMethodWhichNeedAListOfString(CastUtils.<List<String>> cast(myRawList));
     * }
     * @param toCast the object to cast.
     * @return the casted object.
     */
    @SuppressWarnings("unchecked")
    public static <E> E cast(final Object toCast) {
        return (E) toCast;
    }
    
    public static void main(String[] args) {
        Object d= null;
        
        String casted= cast(d);
        
        System.out.println(casted);
    }

}
