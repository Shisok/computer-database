package com.excilys.cdb.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.excilys.cdb.Cdb;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CliMenu.class)
public class TestCli {

	@Test
	public void testCliCompanyList() throws Exception {

		PowerMockito.spy(CliMenu.class);
		CliMenu mockCli = Mockito.spy(CliMenu.class);
		PowerMockito.doReturn(1).when(CliMenu.class, "askInputMainMenu");
//		PowerMockito.doReturn(1).when(CliMenu.class, "askCompanyMenuInput");
		PowerMockito.when(CliMenu.askCompanyMenuInput()).thenReturn(1).thenReturn(5);
		Cdb.main(new String[0]);

	}
}