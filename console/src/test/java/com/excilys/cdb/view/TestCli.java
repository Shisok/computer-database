package com.excilys.cdb.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CliMenu.class)
public class TestCli {

	@Test
	public void testCliCompanyList() throws Exception {

		PowerMockito.spy(CliMenu.class);
		CliMenu mockCli = new CliMenu(null, null, null);
//		PowerMockito.when(CliMenu.askInputMainMenu()).thenReturn(1);
//		PowerMockito.when(CliMenu.askCompanyMenuInput()).thenReturn(1).thenReturn(5);
		PowerMockito.doReturn(1).when(CliMenu.class, "askInputMainMenu");
		mockCli.mainMenu();
	}
}