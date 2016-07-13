package org.rev317.min.accessors;

public interface Interface {

    int[] getItems();

    int[] getStackSizes();

    /**
     * This is meant for the clients that have a long value as their stacksizes, simply because they have no logic...
     *
     * @return long version of #getStackSizes
     */
    long[] getLongStackSizes();

    String getMessage();

}
