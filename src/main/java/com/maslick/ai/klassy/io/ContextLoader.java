package com.maslick.ai.klassy.io;

import java.io.IOException;
import java.io.InputStream;

public class ContextLoader implements IFileLoader{
    @Override
    public InputStream getFile(String filename) throws IOException {
        return ClassLoader.getSystemResourceAsStream(filename);
    }
}
