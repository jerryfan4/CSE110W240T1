package com.cse110w240t1.systemmoniter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fanfan on 3/10/16.
 */
public class RAMFragmentTest {

    private RAMFragment tramFragment;

    @Before
    public void setUp() throws Exception {
        tramFragment = new RAMFragment();
    }

    @Test
    public void testGetTotalMemory() throws Exception {
        assertNotNull(tramFragment.getTotalMemory());
    }

    @Test
    public void testGetAvailableMemory() throws Exception {
        assertNotNull(tramFragment.getAvailableMemory());
    }

    @Test
    public void testGetUsingMemory() throws Exception {
        assertNotNull(tramFragment.getUsingMemory());
    }
}