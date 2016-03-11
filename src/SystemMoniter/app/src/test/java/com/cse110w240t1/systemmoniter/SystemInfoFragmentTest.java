package com.cse110w240t1.systemmoniter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fanfan on 3/9/16.
 */
public class SystemInfoFragmentTest {

    private SystemInfoFragment tsysteminfoFragment;

    @Before
    public void setUp() throws Exception {
        tsysteminfoFragment = new SystemInfoFragment();
    }

    @Test
    public void testGetOS_Version() throws Exception {
        assertNotNull(tsysteminfoFragment.getOS_Version());
    }

    @Test
    public void testGetSecurity_patch() throws Exception {
        assertNotNull(tsysteminfoFragment.getSecurity_patch());
    }

    @Test
    public void testGetPhone_model() throws Exception {
        assertNotNull(tsysteminfoFragment.getPhone_model());
    }

    @Test
    public void testGetManufacturer() throws Exception {
        assertNotNull(tsysteminfoFragment.getManufacturer());
    }

    @Test
    public void testGetSIMCard() throws Exception {
        assertNotNull(tsysteminfoFragment.getSIMCard());
    }

    @Test
    public void testGetSerialNumber() throws Exception {
        assertNotNull(tsysteminfoFragment.getSerialNumber());
    }

    @Test
    public void testGetIMEI() throws Exception {
        assertNotNull(tsysteminfoFragment.getIMEI());
    }
}