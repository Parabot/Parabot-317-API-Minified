package org.rev317.min.accessors;

public interface Interface {

    int[] getItems();

    int[] getStackSizes();

    /**
     * @return long version of #getStackSizes
     */
    long[] getLongStackSizes();

    String getMessage();
}
