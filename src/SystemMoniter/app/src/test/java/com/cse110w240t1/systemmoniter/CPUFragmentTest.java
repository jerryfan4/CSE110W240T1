package com.cse110w240t1.systemmoniter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fanfan on 3/9/16.
 */
public class CPUFragmentTest {

    private CPUFragment tcpuFragment;

    @Before
    public void setUp() throws Exception {
        tcpuFragment = new CPUFragment();
    }

    @Test
    public void testGetMake() throws Exception {
        assertNotNull(tcpuFragment.getMake());
    }

    @Test
    public void testGetModel() throws Exception {
        assertNotNull(tcpuFragment.getModel());
    }

    @Test
    public void testGetArchitecture() throws Exception {
        assertNotNull(tcpuFragment.getArchitecture());
    }
}