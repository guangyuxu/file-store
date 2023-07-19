package com.wov.gavin.fs.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.shell.result.DefaultResultHandler;

@SpringBootTest(properties = {
		InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
		ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"
})
public class CommandClientApplicationTests {

	@Autowired
	private Shell shell;

	@Autowired
	private DefaultResultHandler resultHandler;

	//@Test
	void contextLoads() {
		Object help = shell.evaluate(() -> "help");
		//MatcherAssert.assertThat(help, is(not(nullValue())));

		resultHandler.handleResult(help);
		System.out.println(help);

	}


}
