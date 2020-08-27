package tech.stack.hexo.util;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.util.ResourceUtils;
import tech.stack.hexo.core.exception.AdminException;
import tech.stack.hexo.core.ext.ShellExecResultListOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenjianyuan
 * @date 2020/8/22 23:47:35
 */
public class ShellUtil {

    public static void exec(String command) throws IOException {
        CommandLine commandLine = new CommandLine(command);
        DefaultExecutor executor = new DefaultExecutor();
        int res = executor.execute(commandLine);
        if (res != 0) {
            throw new AdminException(command.concat(" 运行错误."));
        }
    }

    public static int execCmd(String command, String[] params, PumpStreamHandler streamHandler) throws IOException {
        CommandLine commandline = new CommandLine(command);
        if (params != null && params.length > 0) {
            commandline.addArguments(params);
        }
        // execCmd
        DefaultExecutor exec = new DefaultExecutor();
        exec.setStreamHandler(streamHandler);
        // exit code: 0=success, 1=error
        return exec.execute(commandline);
    }

    public static String execRes(String command) throws IOException {
        CommandLine commandLine = CommandLine.parse(command);
        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        executor.setStreamHandler(new PumpStreamHandler(baos, baos));
        int exitValue = executor.execute(commandLine);
        if (exitValue != 0) {
            throw new AdminException(command.concat(" 运行错误."));
        }
        return baos.toString();
    }

    public static List<String> execRes(File file, String... args) throws IOException {
        CommandLine commandLine = new CommandLine(file);
        commandLine.addArguments(args);
        ShellExecResultListOutputStream baos = new ShellExecResultListOutputStream();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(new PumpStreamHandler(baos, baos));
        int exitValue = executor.execute(commandLine);
        return baos.getLines();
    }

    public static void main(String[] args) throws IOException {
        String s = ShellUtil.execRes("cd /Users/chenjianyuan/hexo-blog git remote -v");
        File file = ResourceUtils.getFile("classpath:script/git-remote.sh");
        List<String> res = execRes(ResourceUtils.getFile("classpath:script/git-remote.sh"), "/Users/chenjianyuan/hexo-blog");
        System.out.println(Arrays.toString(res.get(0).split("\\s")));
        System.out.println(res.get(0).split("\\s")[1]);
    }
}
