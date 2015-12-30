package org.rev317.min.accessors;

public interface Interface {

    public int[] getItems();

    public int[] getStackSizes();

    /**
     * This is meant for the clients that have a long value as their stacksizes, simply because they have no logic...
     *
     * @return long version of #getStackSizes
     */
    public long[] getLongStackSizes();

    public String getMessage();

}
