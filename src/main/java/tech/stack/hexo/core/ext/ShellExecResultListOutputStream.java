package tech.stack.hexo.core.ext;

import org.apache.commons.exec.LogOutputStream;

import java.util.LinkedList;
import java.util.List;

/**
 * @author chenjianyuan
 * @date 2020/8/23 02:33:17
 */
public class ShellExecResultListOutputStream extends LogOutputStream {
    private final List<String> lines = new LinkedList<>();

    @Override
    protected void processLine(String line, int logLevel) {
        lines.add(line);
    }

    public List<String> getLines() {
        return lines;
    }
}
