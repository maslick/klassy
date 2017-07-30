package com.maslick.ai.klassy.io;

import java.io.IOException;
import java.io.InputStream;

public interface IFileLoader {
    InputStream getFile(String filename) throws IOException;
}
